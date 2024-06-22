package com.app.jetpack.mvvm.features.artistdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistDetailToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistDetailState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArtistDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getArtistDetailUseCase: GetArtistDetailUseCase =
        mockk<GetArtistDetailUseCase>(relaxed = true)
    private val artistDetailToUiStateMapper: ArtistDetailToUiStateMapper =
        mockk<ArtistDetailToUiStateMapper>(relaxed = true)

    private lateinit var sut: ArtistDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ArtistDetailViewModel(getArtistDetailUseCase, artistDetailToUiStateMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `artistDetail initial state is null`() {
        assertEquals(null, sut.artistDetail.value)
    }

    @Test
    fun `artistDetail - loading`() {
        testScope.launch {
            // Given
            val artistId = 134
            coEvery { getArtistDetailUseCase.invoke(artistId) } returns flow {
                // Simulate delay for loading state
                delay(100)
                emit(DataState.Loading)
            }


            // When
            sut.artistDetail(artistId)

            // Then
            assertEquals(sut.artistDetail.value, DataState.Loading)
        }
    }

    @Test
    fun `artistDetail fetches artist details successfully`() {
        testScope.launch {
            // Given
            val artistId = 134
            val mockArtistDetail = mockk<ArtistDetail>(relaxed = true) {
                every { id } returns artistId
            }
            val mockDataStateSuccess = mockk<DataState.Success<ArtistDetail>>(relaxed = true) {
                every { data } returns mockArtistDetail
            }
            val mockArtistDetailState = mockk<ArtistDetailState>(relaxed = true) {
                every { artistId } returns artistId
            }

            every { mockDataStateSuccess.data } returns mockArtistDetail

            coEvery { getArtistDetailUseCase.invoke(artistId) } returns flow { mockDataStateSuccess }
            every { artistDetailToUiStateMapper.map(mockArtistDetail) } returns mockArtistDetailState


            // When
            sut.artistDetail(artistId)

            // Then
            val expectedState = DataState.Success(mockArtistDetailState)
            assertEquals(mockArtistDetailState.artistId, expectedState.data.artistId)
            coVerify { getArtistDetailUseCase.invoke(artistId) }
            verify { artistDetailToUiStateMapper.map(mockArtistDetail) }
        }
    }

    @Test
    fun `artistDetail fetches artist details with error`() {
        testScope.launch {
            // Given
            val artistId = 134
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = mockk<DataState.Error<ArtistDetail>>(relaxed = true)
            coEvery { getArtistDetailUseCase.invoke(artistId) } returns flow { mockException }

            // When
            sut.artistDetail(artistId)

            //Then
            assertEquals(mockDataStateError, sut.artistDetail.value)
            coVerify { getArtistDetailUseCase.invoke(artistId) }
            verifyNoInteractions(artistDetailToUiStateMapper)
        }
    }
}
