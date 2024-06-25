package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetRecommendedMovieUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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
            val mockDataStateSuccess = Result.success(mockBaseModel)
            coEvery { repository.recommendedMovie(movieId, page) } returns mockDataStateSuccess

            // When
            val result = sut.invoke(params)

            // Then
            coVerify { repository.recommendedMovie(movieId, page) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockBaseModel, result.getOrNull())
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
            val errorMessage = "Network Error"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<BaseModel>(mockException)
            coEvery { repository.recommendedMovie(movieId, page) } returns mockDataStateError


            // When
            val result = sut.invoke(params)

            // Then
            coVerify { repository.recommendedMovie(movieId, page) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
