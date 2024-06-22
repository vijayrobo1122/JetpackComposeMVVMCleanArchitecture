package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote

import app.cash.turbine.test
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
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

class ArtistRemoteDataSourceImplTest {

    private lateinit var sut: ArtistRemoteDataSourceImpl

    private val artistApiService: ArtistApiService =
        mockk<ArtistApiService>(relaxed = true)

    private val artistDetailMapper: ArtistDetailMapper = mockk<ArtistDetailMapper>(relaxed = true)

    private val artistMapper: ArtistMapper = mockk<ArtistMapper>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ArtistRemoteDataSourceImpl(artistApiService, artistDetailMapper, artistMapper)
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
            val mockArtistDetail = mockk<ArtistDetail>(relaxed = true)
            val mockArtistDetailEntity = mockk<ArtistDetailEntity>(relaxed = true)

            // Mock dependencies
            coEvery { artistApiService.artistDetail(artistId) } returns mockArtistDetailEntity
            every { artistDetailMapper.mapTo(mockArtistDetailEntity) } returns mockArtistDetail

            // When
            val artistDetailFlow = sut.artistDetail(artistId)

            // Then
            coVerify(exactly = 1) { artistApiService.artistDetail(artistId) }
            verify { artistDetailMapper.mapTo(mockArtistDetailEntity) }

            artistDetailFlow.test {
                val expectedEmissions =
                    listOf(DataState.Loading, DataState.Success(mockArtistDetail))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `artistDetail fetches artist details with error`() {
        testScope.launch {
            // Given
            val artistId = 123
            val mockException = mockk<Exception>(relaxed = true)
            coEvery { artistApiService.artistDetail(artistId) } throws mockException

            // Trigger artist detail fetch
            val artistDetailFlow = sut.artistDetail(artistId)

            // Then
            coVerify { artistApiService.artistDetail(artistId) }
            verifyNoInteractions(artistDetailMapper)
            artistDetailFlow.test {
                val expectedEmissions = listOf(DataState.Loading, DataState.Error(mockException))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `movieCredit fetches movie credits successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockArtistEntity = mockk<ArtistEntity>(relaxed = true)

            // Mock dependencies
            coEvery { artistApiService.movieCredit(movieId) } returns mockArtistEntity
            every { artistMapper.mapTo(mockArtistEntity) } returns mockArtist

            // When
            val movieCreditFlow = sut.movieCredit(movieId)

            // Then
            coVerify { artistApiService.movieCredit(movieId) }
            verify { artistMapper.mapTo(mockArtistEntity) }
            movieCreditFlow.test {
                val expectedEmissions =
                    listOf(DataState.Loading, DataState.Success(mockArtist))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `movieCredit fetches movie credits with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            coEvery { artistApiService.movieCredit(movieId) } throws mockException

            // Trigger artist detail fetch
            val movieCreditFlow = sut.movieCredit(movieId)

            // Then
            coVerify { artistApiService.movieCredit(movieId) }
            verifyNoInteractions(artistMapper)
            movieCreditFlow.test {
                val expectedEmissions = listOf(DataState.Loading, DataState.Error(mockException))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }
}
