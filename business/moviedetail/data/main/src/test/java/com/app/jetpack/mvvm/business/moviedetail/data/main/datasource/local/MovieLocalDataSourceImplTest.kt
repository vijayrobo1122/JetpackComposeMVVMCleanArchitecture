package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.data.entity.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieLocalDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: MovieLocalDataSourceImpl

    private val favoriteDao: FavoriteDao = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieLocalDataSourceImpl(favoriteDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `likeMovie should call insertFavorite on favoriteDao`() = runTest {
        // Given
        val movieId = 1
        val favoriteEntity = FavoriteEntity(movieId = movieId)

        // When
        sut.likeMovie(movieId)

        // Then
        coVerify { favoriteDao.insertFavorite(favoriteEntity) }
    }

    @Test
    fun `unlikeMovie should call deleteFavorite on favoriteDao`() = runTest {
        // Given
        val movieId = 1

        // When
        sut.unlikeMovie(movieId)

        // Then
        coVerify { favoriteDao.deleteFavorite(movieId) }
    }

    @Test
    fun `isMovieLiked should return true when movie is liked`() = runTest {
        // Given
        val movieId = 1
        coEvery { favoriteDao.isFavorite(movieId) } returns 1

        val result = sut.isMovieLiked(movieId)

        // Then
        assertTrue(result)
    }

    @Test
    fun `isMovieLiked should return false when movie is not liked`() = runTest {
        // Given
        val movieId = 1
        coEvery { favoriteDao.isFavorite(movieId) } returns 0

        val result = sut.isMovieLiked(movieId)

        // Then
        assertFalse(result)
    }
}

