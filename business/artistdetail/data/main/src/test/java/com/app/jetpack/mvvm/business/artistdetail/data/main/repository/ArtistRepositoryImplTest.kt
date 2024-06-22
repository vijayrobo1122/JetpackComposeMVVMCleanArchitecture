package com.app.jetpack.mvvm.business.artistdetail.data.main.repository

import app.cash.turbine.test
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtistRepositoryImplTest {
    private lateinit var sut: ArtistRepositoryImpl

    private val artistRemoteDataSource: ArtistRemoteDataSource =
        mockk<ArtistRemoteDataSource>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ArtistRepositoryImpl(artistRemoteDataSource)
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
            val mockDataStateSuccess = mockk<DataState.Success<ArtistDetail>>(relaxed = true) {
                every { data } returns mockArtistDetail
            }

            // Mock remote data source
            coEvery { artistRemoteDataSource.artistDetail(artistId) } returns flowOf(
                mockDataStateSuccess
            )


            // When
            val artistDetailFlow = sut.artistDetail(artistId)

            // Then
            artistDetailFlow.test {
                // Verify mocks
                coVerify { artistRemoteDataSource.artistDetail(artistId) }

                val item = awaitItem()
                assertEquals(item, mockDataStateSuccess)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `artistDetail fetches artist details with error`() {
        testScope.launch {
            // Given
            val artistId = 123

            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = mockk<DataState.Error<ArtistDetail>>(relaxed = true) {
                every { exception } returns mockException
            }
            coEvery { artistRemoteDataSource.artistDetail(artistId) } returns flow { mockDataStateError }

            // When
            val artistDetailFlow = sut.artistDetail(artistId)

            // Then
            artistDetailFlow.test {
                coVerify { artistRemoteDataSource.artistDetail(artistId) }
                val item = awaitItem()
                assertEquals(item, mockDataStateError)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `movieCredit fetches movie credits successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockDataStateSuccess = mockk<DataState.Success<Artist>>(relaxed = true) {
                every { data } returns mockArtist
            }

            // Mock remote data source
            coEvery { artistRemoteDataSource.movieCredit(movieId) } returns flowOf(
                mockDataStateSuccess
            )


            // When
            val movieCreditFlow = sut.movieCredit(movieId)

            // Then
            movieCreditFlow.test {
                // Verify mocks
                coVerify { artistRemoteDataSource.movieCredit(movieId) }

                val item = awaitItem()
                assertEquals(item, mockDataStateSuccess)
                cancelAndIgnoreRemainingEvents()
            }
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
            val mockDataStateError = mockk<DataState.Error<Artist>>(relaxed = true) {
                every { exception } returns mockException
            }
            coEvery { artistRemoteDataSource.movieCredit(movieId) } returns flow { mockDataStateError }

            // When
            val artistDetailFlow = sut.movieCredit(movieId)

            // Then
            artistDetailFlow.test {
                coVerify { artistRemoteDataSource.movieCredit(movieId) }
                val item = awaitItem()
                assertEquals(item, mockDataStateError)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

}
