package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import app.cash.turbine.test
import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
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

class GetArtistDetailUseCaseTest {

    private lateinit var sut: GetArtistDetailUseCase
    private val repository: ArtistRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetArtistDetailUseCase(repository)
    }

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
            val mockDataStateSuccess = mockk<DataState.Success<ArtistDetail>>(relaxed = true) {
                every { data } returns mockArtistDetail
            }
            coEvery { repository.artistDetail(artistId) } returns flow { mockDataStateSuccess }

            // When
            val result = sut.invoke(artistId)

            // Then
            coVerify { repository.artistDetail(artistId) }
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
            val artistId = 1
            val errorMessage = "Artist not found"
            val mockException = mockk<Exception>(relaxed = true) {
                every { message } returns errorMessage
            }
            val mockDataStateError = mockk<DataState.Error<ArtistDetail>>(relaxed = true) {
                every { exception } returns mockException
            }
            coEvery { repository.artistDetail(artistId) } returns flow { mockDataStateError }


            // When
            val result = sut.invoke(artistId)

            // Then
            coVerify { repository.artistDetail(artistId) }
            result.test {
                val item = awaitItem()
                assertEquals(item, mockDataStateError)
                awaitComplete()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
