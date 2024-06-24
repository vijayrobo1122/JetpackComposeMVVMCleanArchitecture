package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieApiService
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.network.safeApiCall
import javax.inject.Inject


class GenrePagingDataSource @Inject constructor(
    private val apiService: MovieApiService,
    private val baseModelMapper: BaseModelMapper,
    private val genreId: String
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val nextPage = params.key ?: 1
        return safeApiCall {
            apiService.moviesByGenre(nextPage, if (genreId.isNullOrEmpty()) null else genreId)
        }.fold(
            onSuccess = { data ->
                val baseModel = baseModelMapper.mapTo(data)
                LoadResult.Page(
                    data = baseModel.moviesList,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (baseModel.moviesList.isNotEmpty()) baseModel.page + 1 else null
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }
}
