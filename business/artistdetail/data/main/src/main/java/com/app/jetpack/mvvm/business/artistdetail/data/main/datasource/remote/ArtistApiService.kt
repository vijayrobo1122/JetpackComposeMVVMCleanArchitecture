package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.data.main.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistApiService {

    @GET("person/{personId}")
    suspend fun artistDetail(
        @Path("personId") personId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): ArtistDetailEntity

    @GET("movie/{movieId}/credits")
    suspend fun movieCredit(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): ArtistEntity
}
