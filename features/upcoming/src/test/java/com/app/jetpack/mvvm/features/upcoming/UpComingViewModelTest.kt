package com.app.jetpack.mvvm.features.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.FetchLocalGenreListUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetUpcomingMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import com.app.jetpack.mvvm.common.presentation.widgets.model.MovieItemState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpComingViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getUpcomingMoviesUseCase = mockk<GetUpcomingMoviesUseCase>(relaxed = true)
    private val movieItemToUiStateMapper = mockk<MovieItemToUiStateMapper>(relaxed = true)
    private val fetchLocalGenreListUseCase = mockk<FetchLocalGenreListUseCase>(relaxed = true)
    private val genreToUiStateMapper = mockk<GenreToUiStateMapper>(relaxed = true)

    private lateinit var sut: UpComingViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = UpComingViewModel(
            getUpcomingMoviesUseCase,
            movieItemToUiStateMapper,
            fetchLocalGenreListUseCase,
            genreToUiStateMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDefaultGenreItem() {
        val defaultGenre = sut.selectedGenre.value
        assertEquals(DEFAULT_GENRE_ITEM, defaultGenre.name)
    }

    @Test
    fun `test selectedGenre changes filterData`() = runTest {
        // Given
        val genreState = GenreState(id = "1", genreId = 1, name = "Action")

        // When
        sut.selectedGenre = mutableStateOf(genreState)

        val result = sut.selectedGenre.value

        // Then
        assertEquals(1, result.genreId)
        assertEquals("Action", result.name)
    }

    @Test
    fun `test upcomingMovies flow emits data correctly`() = runTest {
        // Given
        val pagingData = PagingData.from(listOf(dummyMovieItem))
        val movieItemState = mockk<MovieItemState>(relaxed = true)

        coEvery { getUpcomingMoviesUseCase.invoke("genreId") } returns flowOf(pagingData)
        every { movieItemToUiStateMapper.map(dummyMovieItem) } returns movieItemState

        testScope.launch {
            sut.upcomingMovies.test {
                verify { movieItemToUiStateMapper.map(dummyMovieItem) }

                val item = awaitItem()
                val movieItems = collectPagingData(item)
                assert(movieItems.isNotEmpty())
                assert(movieItems[0] == movieItemState)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test upcomingMovies flow emits empty data correctly`() = runTest {
        // Given

        val pagingData = PagingData.from(emptyList<MovieItem>())
        val movieItemState = mockk<MovieItemState>(relaxed = true)

        every { movieItemToUiStateMapper.map(dummyMovieItem) } returns movieItemState

        coEvery { getUpcomingMoviesUseCase.invoke("genreId") } returns flowOf(pagingData)

        testScope.launch {
            sut.upcomingMovies.test {
                verify(exactly = 0) { movieItemToUiStateMapper.map(dummyMovieItem) }

                val item = awaitItem()
                val movieItems = collectPagingData(item)
                assert(movieItems.isNotEmpty())
                assert(movieItems[0] == movieItemState)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test upcomingMovies flow handles failure`() = runTest {
        // Given
        val testGenreId = "test_genre"
        sut.genreIdData.value = testGenreId
        val errorMessage = "Network Error"
        val errorFlow: Flow<PagingData<MovieItem>> = flow { throw Exception(errorMessage) }

        // When
        coEvery { getUpcomingMoviesUseCase.invoke("genreId") } returns errorFlow

        // Then
        testScope.launch {
            sut.upcomingMovies.test {
                // Handling error should be tested as well, this might involve catching the error
                // or verifying some error state in the ViewModel.
                try {
                    awaitItem()
                    assert(false) { "Exception was expected but not thrown" }
                } catch (e: Exception) {
                    assert(e.message == errorMessage)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchGenres fetches genres and maps to GenreState with All genre prepended`() {
        val mockGenres = listOf(Genre(genreId = 1, name = "Action"))
        val expectedGenreState = listOf(UpComingViewModel.addGenreState) + mockGenres.map {
            genreToUiStateMapper.map(it)
        }

        coEvery { fetchLocalGenreListUseCase.invoke() } returns mockGenres

        every { genreToUiStateMapper.map(mockGenres.get(0)) } returns expectedGenreState.get(1)

        sut.fetchGenres()
        val values = mutableListOf<List<GenreState>>()
        testScope.launch {
            sut.genreStateList.collect { values.add(it) }

        }
        testScope.advanceUntilIdle()

        assertEquals(listOf(expectedGenreState), values)
    }

    private fun collectPagingData(pagingData: PagingData<MovieItemState>): List<MovieItemState> {
        val items = mutableListOf<MovieItemState>()
        pagingData.map { movieItemState ->
            items.add(movieItemState)
        }
        return items
    }

    companion object {
        val dummyMovieItem = MovieItem(
            id = 1,
            voteCount = 100,
            backdropPath = "/path.jpg",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview",
            posterPath = "/poster.jpg",
            releaseDate = "2023-01-01",
            title = "Title",
            popularity = 8.5,
            voteAverage = 7.5,
            isAdult = false,
            isVideo = false,
            genreIdsList = listOf(1, 2, 3)
        )
    }
}
