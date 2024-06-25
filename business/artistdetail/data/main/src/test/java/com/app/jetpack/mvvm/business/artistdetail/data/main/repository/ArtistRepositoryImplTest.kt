package com.app.jetpack.mvvm.business.artistdetail.data.main.repository

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
import org.mockito.Mockito.verifyNoInteractions

class ArtistRepositoryImplTest {
    private lateinit var sut: ArtistRepositoryImpl

    private val artistRemoteDataSource = mockk<ArtistRemoteDataSource>(relaxed = true)
    private val artistDetailMapper = mockk<ArtistDetailMapper>(relaxed = true)
    private val artistMapper = mockk<ArtistMapper>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ArtistRepositoryImpl(artistRemoteDataSource, artistDetailMapper, artistMapper)
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
            val mockArtistDetail = mockk<ArtistDetail>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtistDetailEntity)
            coEvery { artistRemoteDataSource.artistDetail(artistId) } returns mockDataStateSuccess
            every { artistDetailMapper.mapTo(mockArtistDetailEntity) } returns mockArtistDetail


            // When
            val result = sut.artistDetail(artistId)

            // Then
            coVerify { artistRemoteDataSource.artistDetail(artistId) }
            verify { artistDetailMapper.mapTo(mockArtistDetailEntity) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockDataStateSuccess.getOrNull(), result.getOrNull())
        }
    }

    @Test
    fun `artistDetail fetches artist details with error`() {
        testScope.launch {
            // Given
            val artistId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<ArtistDetailEntity>(mockException)
            coEvery { artistRemoteDataSource.artistDetail(artistId) } returns mockDataStateError

            // When
            val result = sut.artistDetail(artistId)

            // Then
            coVerify { artistRemoteDataSource.artistDetail(artistId) }
            verifyNoInteractions(artistDetailMapper)
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }

    @Test
    fun `movieCredit fetches movie credits successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockArtistEntity = mockk<ArtistEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtistEntity)
            coEvery { artistRemoteDataSource.movieCredit(movieId) } returns mockDataStateSuccess
            every { artistMapper.mapTo(mockArtistEntity) } returns mockArtist

            // When
            val result = sut.movieCredit(movieId)

            // Then
            coVerify { artistRemoteDataSource.movieCredit(movieId) }
            verify { artistMapper.mapTo(mockArtistEntity) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockArtistEntity, result.getOrNull())
        }
    }

    @Test
    fun `movieCredit fetches movie credits with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val errorMessage = "Movie credits fetch Error"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = Result.failure<ArtistEntity>(mockException)
            coEvery { artistRemoteDataSource.movieCredit(movieId) } returns mockDataStateError

            // When
            val result = sut.movieCredit(movieId)

            // Then
            coVerify { artistRemoteDataSource.movieCredit(movieId) }
            verifyNoInteractions(artistMapper)
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }
}
