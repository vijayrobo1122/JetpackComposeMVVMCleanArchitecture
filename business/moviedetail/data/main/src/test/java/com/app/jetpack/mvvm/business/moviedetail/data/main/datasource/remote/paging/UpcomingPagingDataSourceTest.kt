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
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class UpcomingPagingDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: UpcomingPagingDataSource

    private val apiService: MovieApiService = mockk(relaxed = true)
    private val baseModelMapper: BaseModelMapper = mockk(relaxed = true)
    private val genreId = "test_genre_id"

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = UpcomingPagingDataSource(apiService, baseModelMapper, genreId)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load returns Page on successful load`() = runTest {
        testScope.launch {
            // Given
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

            coEvery { apiService.upcomingMovieList(1, genreId) } returns baseModelEntity
            coEvery { baseModelMapper.mapTo(baseModelEntity) } returns baseModel


            val params = LoadParams.Refresh<Int>(null, 2, false)

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
        val exception = IOException("Network error")

        coEvery { apiService.upcomingMovieList(1, genreId) } throws exception
        val params = LoadParams.Refresh<Int>(null, 2, false)

        // When
        val result = sut.load(params)

        // Then
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)
    }

    @Test
    fun `load returns Error on HttpException`() = runTest {
        // Given
        val exception = HttpException(
            Response.error<ResponseBody>(
                500, ResponseBody.create(
                    "application/json".toMediaType(), "Server error"
                )
            )
        )

        coEvery { apiService.upcomingMovieList(1, genreId) } throws exception
        val params = LoadParams.Refresh<Int>(null, 2, false)

        // When

        val result = sut.load(params)

        // Then
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)
    }
}
