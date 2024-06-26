package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
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

class GetGenresListUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetGenresListUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetGenresListUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When genreList usecase invoke should return genres list data from remote api`() = runTest {
        testScope.launch {
            // Given
            val mockGenre = mockk<Genre>(relaxed = true) {
                every { genreId } returns 1
                every { name } returns "Action"
            }
            val mockGenres = listOf(mockGenre)
            val mockDataStateSuccess = Result.success(mockGenres)
            coEvery { repository.genreList() } returns mockDataStateSuccess

            // When
            val result = sut.invoke()

            // Then
            coVerify { repository.genreList() }
            assertEquals(true, result.isSuccess)
            assertEquals(mockGenres, result.getOrNull())
        }
    }

    @Test
    fun `When genreList usecase invoke should return repository throws exception`() {
        testScope.launch {
            // Given
            val errorMessage = "Network Error"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<List<Genre>>(mockException)
            coEvery { repository.genreList() } returns mockDataStateError


            // When
            val result = sut.invoke()

            // Then
            coVerify { repository.genreList() }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
