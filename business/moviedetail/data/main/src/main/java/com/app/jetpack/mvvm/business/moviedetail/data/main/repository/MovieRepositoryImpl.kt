package com.app.jetpack.mvvm.business.moviedetail.data.main.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieApiService
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.GenrePagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.NowPlayingPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.PopularPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.TopRatedPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.paging.UpcomingPagingDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.GenresMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.MovieDetailMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val baseModelMapper: BaseModelMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val genresMapper: GenresMapper,
) : MovieRepository {

    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return flow {
            emit(DataState.Loading)
            try {
                val result = apiService.movieDetail(movieId)
                emit(DataState.Success(movieDetailMapper.mapTo(result)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> {
        return flow {
            emit(DataState.Loading)
            try {
                val searchResult = apiService.recommendedMovie(movieId, page)

                emit(DataState.Success(baseModelMapper.mapTo(searchResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun genreList(): Flow<DataState<Genres>> {
        return flow {
            emit(DataState.Loading)
            try {
                val genreResult = apiService.genreList()
                emit(DataState.Success(genresMapper.mapTo(genreResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun nowPlayingPagingDataSource(genreId: String?): Flow<androidx.paging.PagingData<MovieItem>> {
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

    override fun popularPagingDataSource(genreId: String?): Flow<androidx.paging.PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = { PopularPagingDataSource(apiService, baseModelMapper, genreId) },
            config = PagingConfig(pageSize = 1)
        ).flow
    }

    override fun topRatedPagingDataSource(genreId: String?): Flow<androidx.paging.PagingData<MovieItem>> {
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

    override fun upcomingPagingDataSource(genreId: String?): Flow<androidx.paging.PagingData<MovieItem>> {
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

    override fun genrePagingDataSource(genreId: String): Flow<androidx.paging.PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = { GenrePagingDataSource(apiService, baseModelMapper, genreId) },
            config = PagingConfig(pageSize = 1)
        ).flow
    }
}
