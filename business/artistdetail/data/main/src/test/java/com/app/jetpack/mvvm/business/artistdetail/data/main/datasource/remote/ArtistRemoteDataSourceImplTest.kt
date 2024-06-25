package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.common.network.safeApiCall
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtistRemoteDataSourceImplTest {

    private lateinit var sut: ArtistRemoteDataSourceImpl

    private val artistApiService = mockk<ArtistApiService>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ArtistRemoteDataSourceImpl(artistApiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `artistDetail fetches artist details successfully`() {
        testScope.launch {
            // Given
            val artistId = 123
            val mockArtistDetailEntity = mockk<ArtistDetailEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtistDetailEntity)
            coEvery { artistApiService.artistDetail(artistId) } returns mockArtistDetailEntity
            coEvery { safeApiCall { artistApiService.artistDetail(artistId) } } returns mockDataStateSuccess

            // When
            val result = sut.artistDetail(artistId)

            // Then
            coVerify(exactly = 1) { artistApiService.artistDetail(artistId) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockArtistDetailEntity, result.getOrNull())
        }
    }

    @Test
    fun `artistDetail fetches artist details with error`() {
        testScope.launch {
            // Given
            val artistId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<ArtistDetailEntity>(mockException)
            coEvery { artistApiService.artistDetail(artistId) } throws mockException
            coEvery { safeApiCall { artistApiService.artistDetail(artistId) } } returns mockDataStateError

            // When
            val result = sut.artistDetail(artistId)

            // Then
            coVerify { artistApiService.artistDetail(artistId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }

    @Test
    fun `movieCredit fetches movie credits successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockArtistEntity = mockk<ArtistEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtistEntity)
            coEvery { artistApiService.movieCredit(movieId) } returns mockArtistEntity
            coEvery { safeApiCall { artistApiService.movieCredit(movieId) } } returns mockDataStateSuccess

            // When
            val result = sut.movieCredit(movieId)

            // Then
            coVerify { artistApiService.movieCredit(movieId) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockArtistEntity, result.getOrNull())
        }
    }

    @Test
    fun `movieCredit fetches movie credits with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<ArtistEntity>(mockException)
            coEvery { artistApiService.movieCredit(movieId) } throws mockException
            coEvery { safeApiCall { artistApiService.movieCredit(movieId) } } returns mockDataStateError

            // When
            val result = sut.movieCredit(movieId)

            // Then
            coVerify { artistApiService.movieCredit(movieId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
