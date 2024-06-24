package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity

interface ArtistRemoteDataSource {
    suspend fun artistDetail(artistId: Int): Result<ArtistDetailEntity>

    suspend fun movieCredit(movieId: Int): Result<ArtistEntity>
}
