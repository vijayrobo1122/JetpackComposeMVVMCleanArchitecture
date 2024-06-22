package com.app.jetpack.mvvm.business.moviedetail.data.main.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieRemoteDataSource
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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

class MovieRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: MovieRepositoryImpl

    private val movieLocalDataSource: MovieLocalDataSource =
        mockk<MovieLocalDataSource>(relaxed = true)
    private val movieRemoteDataSource: MovieRemoteDataSource =
        mockk<MovieRemoteDataSource>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `likeMovie should call likeMovie on local data source`() = runTest {
        // Given
        val movieId = 1
        coEvery { movieLocalDataSource.likeMovie(movieId) } just runs

        // When
        sut.likeMovie(movieId)

        // Then
        coVerify { movieLocalDataSource.likeMovie(movieId) }
    }

    @Test
    fun `unlikeMovie should call unlikeMovie on local data source`() = runTest {
        // Given
        val movieId = 1
        coEvery { movieLocalDataSource.unlikeMovie(movieId) } just runs

        // When
        sut.unlikeMovie(movieId)

        // Then
        coVerify { movieLocalDataSource.unlikeMovie(movieId) }
    }

    @Test
    fun `isMovieLiked should return true value from local data source`() = runTest {
        // Given
        val movieId = 1
        val isLiked = true
        coEvery { movieLocalDataSource.isMovieLiked(movieId) } returns isLiked

        // When
        val result = sut.isMovieLiked(movieId)

        // Then
        coVerify { movieLocalDataSource.isMovieLiked(movieId) }
        assert(result == isLiked)
    }

    @Test
    fun `isMovieLiked should return false value from local data source`() = runTest {
        // Given
        val movieId = 1
        val isLiked = false
        coEvery { movieLocalDataSource.isMovieLiked(movieId) } returns isLiked

        // When
        val result = sut.isMovieLiked(movieId)

        // Then
        coVerify { movieLocalDataSource.isMovieLiked(movieId) }
        assert(result == isLiked)
    }

    @Test
    fun `movieDetail should return flow from remote data source`() = runTest {
        testScope.launch {
            // Given
            val movieId = 1
            val movieDetail = mockk<MovieDetail>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Title"
            }
            val mockDataState = DataState.Success(movieDetail)
            coEvery { movieRemoteDataSource.movieDetail(movieId) } returns flow { mockDataState }

            // When
            val result = sut.movieDetail(movieId)

            // Then
            result.test {
                coVerify { movieRemoteDataSource.movieDetail(movieId) }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `recommendedMovie should return flow from remote data source`() = runTest {
        testScope.launch {
            // Given
            val movieId = 1
            val page = 1

            val params = GetRecommendedMovieUseCase.Params(
                movieId = movieId,
                page = page
            )
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockBaseModel = mockk<BaseModel>(relaxed = true) {
                every { page } returns 1
                every { totalPages } returns 110
                every { totalResults } returns 10
                every { moviesList } returns listOf(movieItem)
            }
            val mockDataState = DataState.Success(mockBaseModel)
            coEvery {
                movieRemoteDataSource.recommendedMovie(
                    movieId,
                    page
                )
            } returns flow { mockDataState }

            // When
            val result = sut.recommendedMovie(movieId, page)

            // Then
            result.test {
                coVerify { movieRemoteDataSource.recommendedMovie(movieId, page) }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `genreList should return flow from remote data source`() = runTest {
        testScope.launch {
            // Given
            val mockGenre = mockk<Genre>(relaxed = true) {
                every { id } returns 1
                every { name } returns "Action"
            }
            val mockGenres = mockk<Genres>(relaxed = true) {
                every { genres } returns listOf(mockGenre)
            }
            val mockDataState = DataState.Success(mockGenres)
            coEvery { movieRemoteDataSource.genreList() } returns flow { mockDataState }

            // When
            val result = sut.genreList()

            // Then
            result.test {
                coVerify { movieRemoteDataSource.genreList() }
                val data = awaitItem()
                assertEquals(mockDataState, data)
            }
        }
    }

    @Test
    fun `nowPlayingPagingDataSource should return flow from remote data source`() = runTest {
        testScope.launch {
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { movieRemoteDataSource.nowPlayingPagingDataSource(genreId) } returns successFlow


            val result = sut.nowPlayingPagingDataSource(genreId)

            // Then
            result.test {
                verify { movieRemoteDataSource.nowPlayingPagingDataSource(genreId) }
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
    fun `popularPagingDataSource should return flow from remote data source`() = runTest {
        testScope.launch {
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { movieRemoteDataSource.popularPagingDataSource(genreId) } returns successFlow


            val result = sut.popularPagingDataSource(genreId)

            // Then
            result.test {
                verify { movieRemoteDataSource.popularPagingDataSource(genreId) }
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
    fun `topRatedPagingDataSource should return flow from remote data source`() = runTest {
        testScope.launch {
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { movieRemoteDataSource.topRatedPagingDataSource(genreId) } returns successFlow


            val result = sut.topRatedPagingDataSource(genreId)

            // Then
            result.test {
                verify { movieRemoteDataSource.topRatedPagingDataSource(genreId) }
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
    fun `upcomingPagingDataSource should return flow from remote data source`() = runTest {
        testScope.launch {
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { movieRemoteDataSource.upcomingPagingDataSource(genreId) } returns successFlow


            val result = sut.upcomingPagingDataSource(genreId)

            // Then
            result.test {
                verify { movieRemoteDataSource.upcomingPagingDataSource(genreId) }
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
    fun `genrePagingDataSource should return flow from remote data source`() = runTest {
        testScope.launch {
            val genreId = "124"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 1
                every { title } returns "Movie 1"
            }
            val mockPagingData: PagingData<MovieItem> = PagingData.from(listOf(movieItem))
            val successFlow = flowOf(mockPagingData)
            every { movieRemoteDataSource.genrePagingDataSource(genreId) } returns successFlow


            val result = sut.genrePagingDataSource(genreId)

            // Then
            result.test {
                verify { movieRemoteDataSource.genrePagingDataSource(genreId) }
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
}
