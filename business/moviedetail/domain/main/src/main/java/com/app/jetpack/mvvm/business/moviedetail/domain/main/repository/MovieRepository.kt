package com.app.jetpack.mvvm.business.moviedetail.domain.main.repository


import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow

@SuppressWarnings("TooManyFunctions")
interface MovieRepository {

    suspend fun likeMovie(movieId: Int)

    suspend fun unlikeMovie(movieId: Int)

    suspend fun isMovieLiked(movieId: Int): Boolean

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>>

    suspend fun genreList(): Flow<DataState<Genres>>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>>
}
