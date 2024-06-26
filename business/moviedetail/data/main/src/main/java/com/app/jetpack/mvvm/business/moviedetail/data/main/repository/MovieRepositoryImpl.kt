package com.app.jetpack.mvvm.business.moviedetail.data.main.repository

import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieRemoteDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.GenreMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.MovieDetailMapper
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val baseModelMapper: BaseModelMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val genreMapper: GenreMapper
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

    override suspend fun genreList(): Result<List<Genre>> {
        val genresList = movieLocalDataSource.getAllGenres()
        if (genresList.isEmpty()) {
            val result = movieRemoteDataSource.genreList()
            if (result.isSuccess) {
                result.getOrNull()?.genres?.let { list ->
                    movieLocalDataSource.insertAllGenres(list)
                    return Result.success(list.map { genreMapper.mapTo(it) })
                }
            }
            return Result.success(emptyList())
        } else {
            return Result.success(genresList.map { genreMapper.mapTo(it) })
        }
    }

    override suspend fun movieDetail(movieId: Int): Result<MovieDetail> {
        return movieRemoteDataSource.movieDetail(movieId).map { movieDetailMapper.mapTo(it) }
    }

    override suspend fun recommendedMovie(movieId: Int, page: Int): Result<BaseModel> {
        return movieRemoteDataSource.recommendedMovie(movieId, page)
            .map { baseModelMapper.mapTo(it) }
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

    override suspend fun fetchLocalGenreList(): List<Genre> {
        val genresList = movieLocalDataSource.getAllGenres()
        return genresList.map { genreMapper.mapTo(it) }
    }
}
