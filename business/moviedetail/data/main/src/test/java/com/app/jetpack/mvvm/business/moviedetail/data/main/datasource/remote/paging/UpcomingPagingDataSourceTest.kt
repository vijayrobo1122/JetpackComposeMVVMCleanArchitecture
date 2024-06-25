package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieItemEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieApiService
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.network.safeApiCall
import io.mockk.coEvery
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class UpcomingPagingDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: UpcomingPagingDataSource

    private val apiService: MovieApiService = mockk(relaxed = true)
    private val baseModelMapper: BaseModelMapper = mockk(relaxed = true)
    private val genreId = "test_genre_id"

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = UpcomingPagingDataSource(apiService, baseModelMapper, genreId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load returns Page on successful load`() = runTest {
        testScope.launch {
            // Given
            val params = LoadParams.Refresh<Int>(null, 2, false)
            val genreId = "123"
            val movieItem = mockk<MovieItem>(relaxed = true) {
                every { id } returns 123
            }
            val baseModel = mockk<BaseModel>(relaxed = true) {
                every { moviesList } returns listOf(movieItem)
            }
            val movieItemEntity = mockk<MovieItemEntity>(relaxed = true) {
                every { id } returns 123
            }
            val baseModelEntity = mockk<BaseModelEntity>(relaxed = true) {
                every { results } returns listOf(movieItemEntity)
            }
            val mockDataStateSuccess = Result.success(baseModelEntity)
            coEvery { apiService.upcomingMovieList(1, genreId) } returns baseModelEntity
            coEvery {
                safeApiCall {
                    apiService.upcomingMovieList(
                        1,
                        genreId
                    )
                }
            } returns mockDataStateSuccess
            coEvery { baseModelMapper.mapTo(baseModelEntity) } returns baseModel

            // When
            val result = sut.load(params)

            // Then
            assert(result is PagingSource.LoadResult.Page)
            result as PagingSource.LoadResult.Page
            assertEquals(null, result.prevKey)
            assertEquals(2, result.nextKey)
            assertEquals(listOf(movieItem), result.data)
        }
    }

    @Test
    fun `load returns Error on IOException`() = runTest {
        // Given
        val params = LoadParams.Refresh<Int>(null, 2, false)
        val mockException = mockk<Exception>(relaxed = true)
        val mockDataStateError = Result.failure<BaseModelEntity>(exception = mockException)
        coEvery { apiService.upcomingMovieList(1, genreId) } throws mockException
        coEvery {
            safeApiCall {
                apiService.upcomingMovieList(
                    1,
                    genreId
                )
            }
        } returns mockDataStateError


        // When
        val result = sut.load(params)

        // Then
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(mockException, result.throwable)
    }

    @Test
    fun `load returns Error on HttpException`() = runTest {
        // Given
        val params = LoadParams.Refresh<Int>(null, 2, false)
        val mockException = HttpException(
            Response.error<ResponseBody>(
                500, "Server error"
                    .toResponseBody("application/json".toMediaType())
            )
        )
        val mockDataStateError = Result.failure<BaseModelEntity>(exception = mockException)
        coEvery { apiService.upcomingMovieList(1, genreId) } throws mockException
        coEvery {
            safeApiCall {
                apiService.upcomingMovieList(
                    1,
                    genreId
                )
            }
        } returns mockDataStateError

        // When

        val result = sut.load(params)

        // Then
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(mockException, result.throwable)
    }
}
