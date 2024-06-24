package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieRemoteDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.GenrePagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.NowPlayingPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.PopularPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.TopRatedPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.UpcomingPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val baseModelMapper: BaseModelMapper,
) : MovieRemoteDataSource {

    override suspend fun movieDetail(movieId: Int): Result<MovieDetailEntity> {
        return safeApiCall { apiService.movieDetail(movieId) }
    }

    override suspend fun recommendedMovie(movieId: Int, page: Int): Result<BaseModelEntity> {
        return safeApiCall { apiService.recommendedMovie(movieId, page) }
    }

    override suspend fun genreList(): Result<GenresEntity> {
        return safeApiCall { apiService.genreList() }
    }

    override fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = {
                NowPlayingPagingDataSource(
                    apiService,
                    baseModelMapper,
                    genreId,
                )
            },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

    override fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = { PopularPagingDataSource(apiService, baseModelMapper, genreId) },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

    override fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = {
                TopRatedPagingDataSource(
                    apiService,
                    baseModelMapper,
                    genreId
                )
            },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

    override fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = {
                UpcomingPagingDataSource(
                    apiService,
                    baseModelMapper,
                    genreId
                )
            },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

    override fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = { GenrePagingDataSource(apiService, baseModelMapper, genreId) },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

}
