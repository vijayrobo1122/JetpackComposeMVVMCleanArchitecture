package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.GenresMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.MovieDetailMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
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
import org.mockito.Mockito.verifyNoInteractions

class MovieRemoteDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: MovieRemoteDataSourceImpl

    private val apiService: MovieApiService = mockk(relaxed = true)
    private val baseModelMapper: BaseModelMapper = mockk(relaxed = true)
    private val movieDetailMapper: MovieDetailMapper = mockk(relaxed = true)
    private val genresMapper: GenresMapper = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieRemoteDataSourceImpl(
            apiService, baseModelMapper, movieDetailMapper, genresMapper
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `movieDetail fetches movie details successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockMovieDetail = mockk<MovieDetail>(relaxed = true)
            val mockMovieDetailEntity = mockk<MovieDetailEntity>(relaxed = true)

            // Mock dependencies
            coEvery { apiService.movieDetail(movieId) } returns mockMovieDetailEntity
            every { movieDetailMapper.mapTo(mockMovieDetailEntity) } returns mockMovieDetail

            // When
            val movieDetailFlow = sut.movieDetail(movieId)

            // Then
            coVerify(exactly = 1) { apiService.movieDetail(movieId) }
            verify { movieDetailMapper.mapTo(mockMovieDetailEntity) }

            movieDetailFlow.test {
                val expectedEmissions =
                    listOf(DataState.Loading, DataState.Success(mockMovieDetail))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `movieDetail fetches movie details with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            coEvery { apiService.movieDetail(movieId) } throws mockException

            // Trigger artist detail fetch
            val movieDetailFlow = sut.movieDetail(movieId)

            // Then
            coVerify { apiService.movieDetail(movieId) }
            verifyNoInteractions(movieDetailMapper)
            movieDetailFlow.test {
                val expectedEmissions = listOf(DataState.Loading, DataState.Error(mockException))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }


    @Test
    fun `recommendedMovie fetches recommended movies successfully`() {
        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val mockBaseModel = mockk<BaseModel>(relaxed = true)
            val mockBaseModelEntity = mockk<BaseModelEntity>(relaxed = true)

            // Mock dependencies
            coEvery { apiService.recommendedMovie(movieId, page) } returns mockBaseModelEntity
            every { baseModelMapper.mapTo(mockBaseModelEntity) } returns mockBaseModel

            // When
            val recommendedMovieFlow = sut.recommendedMovie(movieId, page)

            // Then
            coVerify(exactly = 1) { apiService.recommendedMovie(movieId, page) }
            verify { baseModelMapper.mapTo(mockBaseModelEntity) }

            recommendedMovieFlow.test {
                val expectedEmissions =
                    listOf(DataState.Loading, DataState.Success(mockBaseModel))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `recommendedMovie fetches recommended movies with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val mockException = mockk<Exception>(relaxed = true)
            coEvery { apiService.recommendedMovie(movieId, page) } throws mockException

            // Trigger artist detail fetch
            val recommendedMovieFlow = sut.recommendedMovie(movieId, page)

            // Then
            coVerify { apiService.recommendedMovie(movieId, page) }
            verifyNoInteractions(baseModelMapper)
            recommendedMovieFlow.test {
                val expectedEmissions = listOf(DataState.Loading, DataState.Error(mockException))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `genreList fetches genres successfully`() {
        testScope.launch {
            // Given
            val mockGenres = mockk<Genres>(relaxed = true)
            val mockGenresEntity = mockk<GenresEntity>(relaxed = true)

            // Mock dependencies
            coEvery { apiService.genreList() } returns mockGenresEntity
            every { genresMapper.mapTo(mockGenresEntity) } returns mockGenres

            // When
            val genresFlow = sut.genreList()

            // Then
            coVerify(exactly = 1) { apiService.genreList() }
            verify { genresMapper.mapTo(mockGenresEntity) }

            genresFlow.test {
                val expectedEmissions =
                    listOf(DataState.Loading, DataState.Success(mockGenres))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `genreList fetches genres with error`() {
        testScope.launch {
            // Given
            val mockException = mockk<Exception>(relaxed = true)
            coEvery { apiService.genreList() } throws mockException

            // Trigger artist detail fetch
            val genresFlow = sut.genreList()

            // Then
            coVerify { apiService.genreList() }
            verifyNoInteractions(genresMapper)
            genresFlow.test {
                val expectedEmissions = listOf(DataState.Loading, DataState.Error(mockException))

                assertEquals(expectedEmissions, awaitItem())
            }
        }
    }

    @Test
    fun `nowPlayingPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            val flow = sut.nowPlayingPagingDataSource(genreId)

            flow.collect { pagingData ->
                assert(pagingData is PagingData<MovieItem>)
            }
            coVerify { apiService.nowPlayingMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `popularPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            val flow = sut.popularPagingDataSource(genreId)

            flow.collect { pagingData ->
                assert(pagingData is PagingData<MovieItem>)
            }
            coVerify { apiService.popularMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `topRatedPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            val flow = sut.topRatedPagingDataSource(genreId)

            flow.collect { pagingData ->
                assert(pagingData is PagingData<MovieItem>)
            }
            coVerify { apiService.topRatedMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `upcomingPagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            val genreId: String? = null
            val page = 1
            val apiKey = "apiKey"

            val flow = sut.upcomingPagingDataSource(genreId)

            flow.collect { pagingData ->
                assert(pagingData is PagingData<MovieItem>)
            }
            coVerify { apiService.upcomingMovieList(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }

    @Test
    fun `genrePagingDataSource should return PagingData flow`() = runTest {
        testScope.launch {
            val genreId: String = "action"
            val page = 1
            val apiKey = "apiKey"

            val flow = sut.genrePagingDataSource(genreId)

            flow.collect { pagingData ->
                assert(pagingData is PagingData<MovieItem>)
            }
            coVerify { apiService.moviesByGenre(page, genreId, apiKey) }
            verify { baseModelMapper.mapTo(any()) }
        }
    }
}