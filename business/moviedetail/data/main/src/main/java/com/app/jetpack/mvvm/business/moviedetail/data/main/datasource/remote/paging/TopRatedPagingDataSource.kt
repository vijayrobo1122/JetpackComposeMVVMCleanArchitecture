package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieApiService
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class TopRatedPagingDataSource @Inject constructor(
    private val apiService: MovieApiService,
    private val baseModelMapper: BaseModelMapper,
    private val genreId: String?,
) :
    PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val baseModelEntity = apiService.topRatedMovieList(
                nextPage,
                if (genreId.isNullOrEmpty()) null else genreId
            )
            val baseModel = baseModelMapper.mapTo(baseModelEntity)
            LoadResult.Page(
                data = baseModel.moviesList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (baseModel.moviesList.isNotEmpty()) baseModel.page + 1 else null
            )
        } catch (exception: IOException) {
            Timber.e("exception ${exception.message}")
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            return LoadResult.Error(httpException)
        }
    }
}
