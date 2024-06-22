package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import app.cash.turbine.test
import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.common.domain.models.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
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

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetMovieCreditUseCase(repository)
    }

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
            val mockDataStateSuccess = mockk<DataState.Success<Artist>>(relaxed = true) {
                every { data } returns mockArtist
            }
            coEvery { repository.movieCredit(movieId) } returns flow { mockDataStateSuccess }

            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.movieCredit(movieId) }
            result.test {
                val item = awaitItem()
                assertEquals(item, mockDataStateSuccess)
                awaitComplete()
                cancelAndIgnoreRemainingEvents()
            }
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
            val mockDataStateError = mockk<DataState.Error<Artist>>(relaxed = true) {
                every { exception } returns mockException
            }
            coEvery { repository.movieCredit(movieId) } returns flow { mockDataStateError }


            // When
            val result = sut.invoke(movieId)

            // Then
            coVerify { repository.movieCredit(movieId) }
            result.test {
                val item = awaitItem()
                assertEquals(item, mockDataStateError)
                awaitComplete()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
