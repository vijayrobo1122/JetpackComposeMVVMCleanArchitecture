package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource

import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface ArtistRemoteDataSource {
    suspend fun artistDetail(artistId: Int): Flow<DataState<ArtistDetail>>

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>>
}
