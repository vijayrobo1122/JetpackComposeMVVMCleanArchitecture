package com.app.jetpack.mvvm.business.artistdetail.domain.main.repository

import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail

interface ArtistRepository {

    suspend fun artistDetail(artistId: Int): Result<ArtistDetail>

    suspend fun movieCredit(movieId: Int): Result<Artist>

}
