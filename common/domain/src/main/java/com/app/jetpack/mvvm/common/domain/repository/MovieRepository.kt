package com.app.jetpack.mvvm.common.domain.repository

import androidx.paging.PagingData
import com.app.jetpack.mvvm.common.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.model.artist.Artist
import com.app.jetpack.mvvm.common.domain.model.artist.ArtistDetail
import com.app.jetpack.mvvm.common.domain.model.moviedetail.MovieDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>>

    suspend fun search(searchKey: String): Flow<DataState<BaseModel>>

    suspend fun genreList(): Flow<DataState<Genres>>

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>>

    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>>

    fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>>
}
