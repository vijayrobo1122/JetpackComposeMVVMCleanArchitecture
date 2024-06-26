package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
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
import org.junit.Rule
import org.junit.Test

class FetchLocalGenreListUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: FetchLocalGenreListUseCase

    private val repository: MovieRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = FetchLocalGenreListUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When fetchLocalGenreList usecase invoke should return genres list data from database`() =
        runTest {
            testScope.launch {
                // Given
                val mockGenre = mockk<Genre>(relaxed = true) {
                    every { genreId } returns 1
                    every { name } returns "Action"
                }
                val mockGenres = listOf(mockGenre)
                coEvery { repository.fetchLocalGenreList() } returns mockGenres

                // When
                val result = sut.invoke()

                // Then
                coVerify { repository.genreList() }
                assertEquals(mockGenres, result)
            }
        }
}
