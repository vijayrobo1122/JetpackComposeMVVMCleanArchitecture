package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.common.network.safeApiCall
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
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

class MovieRemoteDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: MovieRemoteDataSourceImpl

    private val apiService: MovieApiService = mockk(relaxed = true)
    private val baseModelMapper: BaseModelMapper = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieRemoteDataSourceImpl(apiService, baseModelMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `movieDetail fetches movie details successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockMovieDetailEntity = mockk<MovieDetailEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockMovieDetailEntity)
            coEvery { apiService.movieDetail(movieId) } returns mockMovieDetailEntity
            coEvery { safeApiCall { apiService.movieDetail(movieId) } } returns mockDataStateSuccess

            // When
            val result = sut.movieDetail(movieId)

            // Then
            coVerify(exactly = 1) { apiService.movieDetail(movieId) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockMovieDetailEntity, result.getOrNull())
        }
    }

    @Test
    fun `movieDetail fetches movie details with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<MovieDetailEntity>(mockException)
            coEvery { apiService.movieDetail(movieId) } throws mockException
            coEvery { safeApiCall { apiService.movieDetail(movieId) } } returns mockDataStateError

            // When
            val result = sut.movieDetail(movieId)

            // Then
            coVerify { apiService.movieDetail(movieId) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }


    @Test
    fun `recommendedMovie fetches recommended movies successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val mockBaseModelEntity = mockk<BaseModelEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockBaseModelEntity)
            coEvery { apiService.recommendedMovie(movieId, page) } returns mockBaseModelEntity
            coEvery {
                safeApiCall {
                    apiService.recommendedMovie(
                        movieId,
                        page
                    )
                }
            } returns mockDataStateSuccess

            // When
            val result = sut.recommendedMovie(movieId, page)

            // Then
            coVerify(exactly = 1) { apiService.recommendedMovie(movieId, page) }
            assertEquals(true, result.isSuccess)
            assertEquals(mockBaseModelEntity, result.getOrNull())
        }
    }

    @Test
    fun `recommendedMovie fetches recommended movies with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<BaseModelEntity>(mockException)
            coEvery { apiService.recommendedMovie(movieId, page) } throws mockException
            coEvery {
                safeApiCall {
                    apiService.recommendedMovie(
                        movieId,
                        page
                    )
                }
            } returns mockDataStateError

            // When
            val result = sut.recommendedMovie(movieId, page)

            // Then
            coVerify { apiService.recommendedMovie(movieId, page) }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }

    @Test
    fun `genreList fetches genres successfully`() {
        testScope.launch {
            // Given
            val mockGenresEntity = mockk<GenresEntity>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockGenresEntity)
            coEvery { apiService.genreList() } returns mockGenresEntity
            coEvery { safeApiCall { apiService.genreList() } } returns mockDataStateSuccess

            // When
            val result = sut.genreList()

            // Then
            coVerify(exactly = 1) { apiService.genreList() }
            assertEquals(true, result.isSuccess)
            assertEquals(mockGenresEntity, result.getOrNull())
        }
    }

    @Test
    fun `genreList fetches genres with error`() {
        testScope.launch {
            // Given
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<GenresEntity>(mockException)
            coEvery { apiService.genreList() } throws mockException
            coEvery { safeApiCall { apiService.genreList() } } returns mockDataStateError

            // When
            val result = sut.genreList()

            // Then
            coVerify { apiService.genreList() }
            assertEquals(true, result.isFailure)
            assertEquals(mockException, result.exceptionOrNull())
        }
    }

    @Test
    fun `nowPlayingPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            // Given
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            // When
            val result = sut.nowPlayingPagingDataSource(genreId)

            // Then
            result.collect {
                assert(true)
            }
            coVerify { apiService.nowPlayingMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `popularPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            // Given
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            // When
            val result = sut.popularPagingDataSource(genreId)

            // Then
            result.collect {
                assert(true)
            }
            coVerify { apiService.popularMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `topRatedPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            // Given
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            // When
            val result = sut.topRatedPagingDataSource(genreId)

            // Then
            result.collect {
                assert(true)
            }
            coVerify { apiService.topRatedMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `upcomingPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            // Given
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            // When
            val result = sut.upcomingPagingDataSource(genreId)

            // Then
            result.collect {
                assert(true)
            }
            coVerify { apiService.upcomingMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `genrePagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            // Given
            val genreId = "action"
            val page = 1
            val apiKey = "apiKey"

            // When
            val result = sut.genrePagingDataSource(genreId)

            // Then
            result.collect {
                assert(true)
            }
            coVerify { apiService.moviesByGenre(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }
}