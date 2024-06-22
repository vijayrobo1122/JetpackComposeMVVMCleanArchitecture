package com.app.jetpack.mvvm.features.genredetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenreMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieItemToUiStateMapper
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GenreDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getGenreMoviesUseCase: GetGenreMoviesUseCase =
        mockk<GetGenreMoviesUseCase>(relaxed = true)
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper =
        mockk<MovieItemToUiStateMapper>(relaxed = true)

    private lateinit var sut: GenreDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GenreDetailViewModel(getGenreMoviesUseCase, movieItemToUiStateMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `moviesByGenre - success`() = runTest {
        testScope.launch {
            // Given
            val genreId = "123"
            val mockMovieItems = listOf(
                dummyMovieItem,
                dummyMovieItem.copy(id = 2, title = "Again")
            )
            val mockPagingData = PagingData.from(mockMovieItems)
            coEvery { getGenreMoviesUseCase.invoke(genreId) } returns flowOf(mockPagingData)


            // When
            val result = sut.moviesByGenre(genreId).toList()

            // Given
            assertEquals(result.size, 1)
        }
    }

    @Test
    fun `moviesByGenre fetches genre movies with error`() {
        testScope.launch {
            // Given
            val genreId = "123"
            val errorMessage = "Network Error"
            val errorFlow: Flow<PagingData<MovieItem>> = flow { throw Exception(errorMessage) }
            coEvery { getGenreMoviesUseCase.invoke(genreId) } returns errorFlow


            // When
            val movieFlow = sut.moviesByGenre(genreId)

            // Then
            movieFlow.test {

                verify { getGenreMoviesUseCase.invoke(genreId) }
                verifyNoInteractions(movieItemToUiStateMapper)
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
