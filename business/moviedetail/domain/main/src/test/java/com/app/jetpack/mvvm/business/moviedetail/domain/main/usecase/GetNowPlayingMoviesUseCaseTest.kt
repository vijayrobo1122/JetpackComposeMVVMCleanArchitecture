package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
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
import org.junit.Rule
import org.junit.Test

class GetNowPlayingMoviesUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: GetNowPlayingMoviesUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = GetNowPlayingMoviesUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given genreId when usecase invoke should return paging data`() {
        testScope.launch {
            // Given
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { repository.nowPlayingPagingDataSource(genreId) } returns successFlow


            // When
            val result = sut.invoke(genreId)

            // Then
            result.test {
                verify { repository.nowPlayingPagingDataSource(genreId) }
                val pagingData = awaitItem()
                val collectedItems = mutableListOf<MovieItem>()
                pagingData.map { item ->
                    collectedItems.add(item)
                }
                assertEquals(movieItem.id, collectedItems.size)
                assertEquals(movieItem.title, collectedItems[0].title)
            }
        }
    }

    @Test
    fun `given genreId when usecase invoke emits empty PagingData when repository returns empty data`() {
        testScope.launch {
            // Given
            val genreId = "123"
            val mockPagingData: PagingData<MovieItem> = PagingData.empty()
            val emptyFlow = flowOf(mockPagingData)
            every { repository.nowPlayingPagingDataSource(genreId) } returns emptyFlow

            // When
            val result = sut.invoke(genreId)

            // Then
            result.test {
                verify { repository.nowPlayingPagingDataSource(genreId) }
                val pagingData = awaitItem()
                val collectedItems = mutableListOf<MovieItem>()
                pagingData.map { item ->
                    collectedItems.add(item)
                }
                assertEquals(mockPagingData, pagingData)
            }
        }
    }

    @Test
    fun `given genreId when usecase invoke throws exception when repository throws exception`() {
        testScope.launch {
            // Given
            val genreId = "123"

            val error = Exception("Network Error")

            every { repository.nowPlayingPagingDataSource(genreId) } returns flow { throw error }

            // When
            val result = sut.invoke(genreId)

            // Then
            result.test {
                verify { repository.nowPlayingPagingDataSource(genreId) }
                try {
                    awaitItem()
                    assert(false) { "Exception was expected but not thrown" }
                } catch (e: Exception) {
                    assert(e.message == error.message)
                }
            }
        }
    }
}
