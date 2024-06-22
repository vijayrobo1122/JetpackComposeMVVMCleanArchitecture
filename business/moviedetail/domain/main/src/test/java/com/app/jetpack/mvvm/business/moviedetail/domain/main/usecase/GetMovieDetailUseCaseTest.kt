package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
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

class GetMovieDetailUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetMovieDetailUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetMovieDetailUseCase(repository)
    }

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
            val mockDataState = DataState.Success(mockMovieDetail)
            coEvery { repository.movieDetail(movieId) } returns flowOf(mockDataState)

            // When
            val result = sut.invoke(movieId)

            // Then
            result.test {
                coVerify { repository.movieDetail(movieId) }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `given movie id when usecase invoke throws exception when repository throws exception`() {
        testScope.launch {
            // Given
            val movieId = 1
            val error = Exception("Network Error")
            coEvery { repository.movieDetail(movieId) } returns flow { throw error }


            // When
            val result = sut.invoke(movieId)

            // Then
            result.test {
                coVerify { repository.movieDetail(movieId) }
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
