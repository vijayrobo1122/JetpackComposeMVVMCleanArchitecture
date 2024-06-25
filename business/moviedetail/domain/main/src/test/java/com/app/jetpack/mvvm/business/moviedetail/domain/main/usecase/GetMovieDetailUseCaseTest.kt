package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
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

class GetMovieDetailUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetMovieDetailUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetMovieDetailUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given movie id when usecase invoke should return recommended movie data`() = runTest {
        testScope.launch {
            // Given
            val movieId = 1
            val mockMovieDetail = mockk<MovieDetail>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie Title"
            }
            val mockDataStateSuccess = Result.success(mockMovieDetail)
            coEvery { repository.movieDetail(movieId) } returns mockDataStateSuccess

            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.genreList() }
            assertEquals(true, result.isSuccess)
            assertEquals(mockMovieDetail, result.getOrNull())
        }
    }

    @Test
    fun `given movie id when usecase invoke throws exception when repository throws exception`() {
        testScope.launch {
            // Given
            val movieId = 1
            val errorMessage = "Network Error"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<MovieDetail>(mockException)
            coEvery { repository.movieDetail(movieId) } returns mockDataStateError


            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.movieDetail(movieId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
