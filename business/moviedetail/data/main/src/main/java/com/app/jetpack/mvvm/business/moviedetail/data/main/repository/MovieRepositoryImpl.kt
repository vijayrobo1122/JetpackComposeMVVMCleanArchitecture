package com.app.jetpack.mvvm.business.moviedetail.data.main.repository

import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieRemoteDataSource
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override suspend fun likeMovie(movieId: Int) {
        movieLocalDataSource.likeMovie(movieId)
    }

    override suspend fun unlikeMovie(movieId: Int) {
        movieLocalDataSource.unlikeMovie(movieId)
    }

    override suspend fun isMovieLiked(movieId: Int): Boolean {
        return movieLocalDataSource.isMovieLiked(movieId)
    }

    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return movieRemoteDataSource.movieDetail(movieId)
    }

    override suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> {
        return movieRemoteDataSource.recommendedMovie(movieId, page)
    }

    override suspend fun genreList(): Flow<DataState<Genres>> {
        return movieRemoteDataSource.genreList()
    }

    override fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return movieRemoteDataSource.nowPlayingPagingDataSource(genreId)
    }

    override fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return movieRemoteDataSource.popularPagingDataSource(genreId)
    }

    override fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return movieRemoteDataSource.topRatedPagingDataSource(genreId)
    }

    override fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> {
        return movieRemoteDataSource.upcomingPagingDataSource(genreId)
    }

    override fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>> {
        return movieRemoteDataSource.genrePagingDataSource(genreId)
    }
}
