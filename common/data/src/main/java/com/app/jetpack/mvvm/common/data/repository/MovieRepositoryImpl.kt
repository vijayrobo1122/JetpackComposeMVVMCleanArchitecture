package com.app.jetpack.mvvm.common.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.jetpack.mvvm.common.data.datasource.remote.ApiService
import com.app.jetpack.mvvm.common.data.datasource.remote.paging.GenrePagingDataSource
import com.app.jetpack.mvvm.common.data.datasource.remote.paging.NowPlayingPagingDataSource
import com.app.jetpack.mvvm.common.data.datasource.remote.paging.PopularPagingDataSource
import com.app.jetpack.mvvm.common.data.datasource.remote.paging.TopRatedPagingDataSource
import com.app.jetpack.mvvm.common.data.datasource.remote.paging.UpcomingPagingDataSource
import com.app.jetpack.mvvm.common.data.mapper.BaseModelMapper
import com.app.jetpack.mvvm.common.data.mapper.GenresMapper
import com.app.jetpack.mvvm.common.data.mapper.artist.ArtistDetailMapper
import com.app.jetpack.mvvm.common.data.mapper.artist.ArtistMapper
import com.app.jetpack.mvvm.common.data.mapper.moviesdetail.MovieDetailMapper
import com.app.jetpack.mvvm.common.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.model.artist.Artist
import com.app.jetpack.mvvm.common.domain.model.artist.ArtistDetail
import com.app.jetpack.mvvm.common.domain.model.moviedetail.MovieDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseModelMapper: BaseModelMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val genresMapper: GenresMapper,
    private val artistMapper: ArtistMapper,
    private val artistDetailMapper: ArtistDetailMapper,
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

    override suspend fun recommendedMovie(
        movieId: Int,
        page: Int
    ): Flow<DataState<BaseModel>> {
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

    override suspend fun search(searchKey: String): Flow<DataState<BaseModel>> {
        return flow {
            emit(DataState.Loading)
            try {
                val searchResult = apiService.search(searchKey)

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

    override suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> {
        return flow {
            emit(DataState.Loading)
            try {
                val artistResult = apiService.movieCredit(movieId)
                emit(DataState.Success(artistMapper.mapTo(artistResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> {
        return flow {
            emit(DataState.Loading)
            try {
                val artistDetailResult = apiService.artistDetail(personId)
                emit(DataState.Success(artistDetailMapper.mapTo(artistDetailResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
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

