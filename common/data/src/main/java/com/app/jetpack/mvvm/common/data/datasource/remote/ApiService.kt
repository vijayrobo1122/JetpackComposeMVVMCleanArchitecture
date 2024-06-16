package com.app.jetpack.mvvm.common.data.datasource.remote

import com.app.jetpack.mvvm.common.data.BuildConfig
import com.app.jetpack.mvvm.common.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.common.data.entity.GenresEntity
import com.app.jetpack.mvvm.common.data.entity.artist.ArtistDetailEntity
import com.app.jetpack.mvvm.common.data.entity.artist.ArtistEntity
import com.app.jetpack.mvvm.common.data.entity.moviedetail.MovieDetailEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun nowPlayingMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("movie/popular")
    suspend fun popularMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("movie/top_rated")
    suspend fun topRatedMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("movie/upcoming")
    suspend fun upcomingMovieList(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("movie/{movieId}")
    suspend fun movieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieDetailEntity

    @GET("movie/{movieId}/recommendations")
    suspend fun recommendedMovie(
        @Path("movieId") movieId: Int,
        @Query("page") one: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("search/movie?page=1&include_adult=false")
    suspend fun search(
        @Query("query") searchKey: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("genre/movie/list")
    suspend fun genreList(@Query("api_key") api_key: String = BuildConfig.API_KEY): GenresEntity

    @GET("discover/movie")
    suspend fun moviesByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): BaseModelEntity

    @GET("movie/{movieId}/credits")
    suspend fun movieCredit(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): ArtistEntity

    @GET("person/{personId}")
    suspend fun artistDetail(
        @Path("personId") personId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): ArtistDetailEntity
}
