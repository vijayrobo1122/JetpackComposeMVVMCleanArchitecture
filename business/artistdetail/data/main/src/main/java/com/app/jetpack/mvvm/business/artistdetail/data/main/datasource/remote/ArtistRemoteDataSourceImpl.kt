package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.common.network.safeApiCall
import javax.inject.Inject

class ArtistRemoteDataSourceImpl @Inject constructor(
    private val artistApiService: ArtistApiService,
    private val artistDetailMapper: ArtistDetailMapper,
    private val artistMapper: ArtistMapper,
) : ArtistRemoteDataSource {

    override suspend fun artistDetail(artistId: Int): Result<ArtistDetailEntity> {
        return safeApiCall { artistApiService.artistDetail(artistId) }
    }

    override suspend fun movieCredit(movieId: Int): Result<ArtistEntity> {
        return safeApiCall { artistApiService.movieCredit(movieId) }
    }
}
