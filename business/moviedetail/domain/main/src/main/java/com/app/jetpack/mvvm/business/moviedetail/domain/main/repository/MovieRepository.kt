package com.app.jetpack.mvvm.business.moviedetail.domain.main.repository


import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

@SuppressWarnings("TooManyFunctions")
interface MovieRepository {

    suspend fun likeMovie(movieId: Int)

    suspend fun unlikeMovie(movieId: Int)

    suspend fun isMovieLiked(movieId: Int): Boolean

    suspend fun genreList(): Result<List<Genre>>

    suspend fun movieDetail(movieId: Int): Result<MovieDetail>

    suspend fun recommendedMovie(movieId: Int, page: Int): Result<BaseModel>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>>

    suspend fun fetchLocalGenreList(): List<Genre>
}
