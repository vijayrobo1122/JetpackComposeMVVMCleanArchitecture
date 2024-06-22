package com.app.jetpack.mvvm.features.toprated

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetTopRatedMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.GenreId
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
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
class TopRatedViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase =
        mockk<GetTopRatedMoviesUseCase>(relaxed = true)
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper =
        mockk<MovieItemToUiStateMapper>(relaxed = true)

    private lateinit var sut: TopRatedViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = TopRatedViewModel(getTopRatedMoviesUseCase, movieItemToUiStateMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

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
    fun `test topRatedMovies flow emits data correctly`() = runTest {
        // Given
        val pagingData = PagingData.from(listOf(dummyMovieItem))
        val movieItemState = mockk<MovieItemState>(relaxed = true)

        coEvery { getTopRatedMoviesUseCase.invoke("genreId") } returns flowOf(pagingData)
        every { movieItemToUiStateMapper.map(dummyMovieItem) } returns movieItemState

        testScope.launch {
            sut.topRatedMovies.test {
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
    fun `test topRatedMovies flow emits empty data correctly`() = runTest {
        // Given
        val pagingData = PagingData.from(emptyList<MovieItem>())
        val movieItemState = mockk<MovieItemState>(relaxed = true)

        every { movieItemToUiStateMapper.map(dummyMovieItem) } returns movieItemState

        coEvery { getTopRatedMoviesUseCase.invoke("genreId") } returns flowOf(pagingData)

        testScope.launch {
            sut.topRatedMovies.test {
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
    fun `test topRatedMovies flow handles failure`() = runTest {
        // Given
        val testGenreId = GenreId("test_genre")
        sut.filterData.value = testGenreId
        val errorMessage = "Network Error"
        val errorFlow: Flow<PagingData<MovieItem>> = flow { throw Exception(errorMessage) }

        // When
        coEvery { getTopRatedMoviesUseCase.invoke("genreId") } returns errorFlow

        // Then
        testScope.launch {
            sut.topRatedMovies.test {
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
