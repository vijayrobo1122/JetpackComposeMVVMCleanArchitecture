package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource

import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun movieDetail(movieId: Int): Result<MovieDetailEntity>

    suspend fun recommendedMovie(movieId: Int, page: Int): Result<BaseModelEntity>

    suspend fun genreList(): Result<GenresEntity>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>>
}
