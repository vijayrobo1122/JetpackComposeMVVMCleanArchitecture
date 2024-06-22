package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IsMovieFavoriteUseCaseTest {

    private lateinit var sut: IsMovieFavoriteUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = IsMovieFavoriteUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke isMovieLiked from repository when successful true`() = runTest {
        testScope.launch {
            // Given
            val movieId = 124
            coEvery { repository.isMovieLiked(movieId) } returns true

            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.isMovieLiked(movieId) }
            assertEquals(true, result)
        }
    }

    @Test
    fun `invoke isMovieLiked from repository when successful false`() = runTest {
        testScope.launch {
            // Given
            val movieId = 124
            coEvery { repository.isMovieLiked(movieId) } returns false

            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.isMovieLiked(movieId) }
            assertEquals(false, result)
        }
    }
}
