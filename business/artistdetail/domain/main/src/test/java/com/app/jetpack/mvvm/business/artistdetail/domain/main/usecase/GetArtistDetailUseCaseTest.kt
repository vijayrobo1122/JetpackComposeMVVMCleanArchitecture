package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
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

class GetArtistDetailUseCaseTest {

    private lateinit var sut: GetArtistDetailUseCase
    private val repository: ArtistRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetArtistDetailUseCase(repository)
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
            val artistId = 1
            val mockArtistDetail = mockk<ArtistDetail>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtistDetail)
            coEvery { repository.artistDetail(artistId) } returns mockDataStateSuccess

            // When
            val result = sut.invoke(artistId)

            // Then
            coVerify { repository.artistDetail(artistId) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockArtistDetail, result.getOrNull())
        }
    }

    @Test
    fun `invoke with invalid artist id should return error state`() {
        testScope.launch {
            // Given
            val artistId = 1
            val errorMessage = "Artist not found"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<ArtistDetail>(mockException)
            coEvery { repository.artistDetail(artistId) } returns mockDataStateError


            // When
            val result = sut.invoke(artistId)

            // Then
            coVerify { repository.artistDetail(artistId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
