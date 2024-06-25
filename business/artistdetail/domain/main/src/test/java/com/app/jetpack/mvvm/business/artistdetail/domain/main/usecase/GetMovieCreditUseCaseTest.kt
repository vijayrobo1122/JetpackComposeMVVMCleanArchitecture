package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
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
import org.junit.Test

class GetMovieCreditUseCaseTest {

    private lateinit var sut: GetMovieCreditUseCase
    private val repository: ArtistRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetMovieCreditUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke fetches artist details from repository when successful`() = runTest {
        testScope.launch {
            // Given
            val movieId = 1
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtist)
            coEvery { repository.movieCredit(movieId) } returns mockDataStateSuccess

            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.movieCredit(movieId) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockArtist, result.getOrNull())
        }
    }

    @Test
    fun `invoke with invalid artist id should return error state`() {
        testScope.launch {
            // Given
            val movieId = 1
            val errorMessage = "Movie not found"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<Artist>(mockException)
            coEvery { repository.movieCredit(movieId) } returns mockDataStateError


            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.movieCredit(movieId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
