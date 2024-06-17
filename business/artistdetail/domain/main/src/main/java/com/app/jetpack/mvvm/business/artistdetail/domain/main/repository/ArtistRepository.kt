package com.app.jetpack.mvvm.business.artistdetail.domain.main.repository

import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {

    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>>

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>>

}
