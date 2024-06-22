package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
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

class GetRecommendedMovieUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetRecommendedMovieUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetRecommendedMovieUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given params when usecase invoke should return recommended movie data`() = runTest {
        testScope.launch {
            // Given
            val movieId = 1
            val page = 1

            val params = GetRecommendedMovieUseCase.Params(
                movieId = movieId,
                page = page
            )
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockBaseModel = mockk<BaseModel>(relaxed = true) {
                every { page } returns 1
                every { totalPages } returns 110
                every { totalResults } returns 10
                every { moviesList } returns listOf(movieItem)
            }
            val mockDataState = DataState.Success(mockBaseModel)
            coEvery { repository.recommendedMovie(movieId, page) } returns flowOf(mockDataState)

            // When
            val result = sut.invoke(params)

            // Then
            result.test {
                coVerify { repository.recommendedMovie(movieId, page) }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `given params when usecase invoke throws exception when repository throws exception`() {
        testScope.launch {
            // Given
            val movieId = 1
            val page = 1
            val params = GetRecommendedMovieUseCase.Params(
                movieId = movieId,
                page = page
            )
            val error = Exception("Network Error")
            coEvery { repository.recommendedMovie(movieId, page) } returns flow { throw error }


            // When
            val result = sut.invoke(params)

            // Then
            result.test {
                coVerify { repository.recommendedMovie(movieId, page) }
                try {
                    awaitItem()
                    assert(false) { "Exception was expected but not thrown" }
                } catch (e: Exception) {
                    assert(e.message == error.message)
                }
            }
        }
    }
}
