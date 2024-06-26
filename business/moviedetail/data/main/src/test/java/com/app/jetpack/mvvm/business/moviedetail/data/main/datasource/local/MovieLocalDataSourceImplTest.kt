package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.GenreDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    private val genreDao: GenreDao = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieLocalDataSourceImpl(favoriteDao, genreDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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


    @Test
    fun `getAllGenres should return list of genres`() = runTest {
        // Given
        val data = listOf(
            GenreEntity(1, genreId = 1, "Action"),
            GenreEntity(2, genreId = 2, "Adventure"),
        )
        coEvery { genreDao.getAllData() } returns data

        val result = sut.getAllGenres()

        // Then
        assertEquals(data, result)
    }

    @Test
    fun `insert all GenreEntity should call insertAllGenres on genreDao`() = runTest {
        // Given
        val data = listOf(
            GenreEntity(1, genreId = 1, "Action"),
            GenreEntity(2, genreId = 2, "Adventure"),
        )

        // When
        sut.insertAllGenres(data)

        // Then
        coVerify { genreDao.insertAll(data) }
    }
}

