package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
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

class GetGenresListUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetGenresListUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetGenresListUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given movie id when usecase invoke should return recommended movie data`() = runTest {
        testScope.launch {
            // Given
            val mockGenre = mockk<Genre>(relaxed = true) {
                every { id } returns 1
                every { name } returns "Action"
            }
            val mockGenres = mockk<Genres>(relaxed = true) {
                every { genres } returns listOf(mockGenre)
            }
            val mockDataState = DataState.Success(mockGenres)
            coEvery { repository.genreList() } returns flowOf(mockDataState)

            // When
            val result = sut.invoke()

            // Then
            result.test {
                coVerify { repository.genreList() }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `given movie id when usecase invoke throws exception when repository throws exception`() {
        testScope.launch {
            // Given
            val error = Exception("Network Error")
            coEvery { repository.genreList() } returns flow { throw error }


            // When
            val result = sut.invoke()

            // Then
            result.test {
                coVerify { repository.genreList() }
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
