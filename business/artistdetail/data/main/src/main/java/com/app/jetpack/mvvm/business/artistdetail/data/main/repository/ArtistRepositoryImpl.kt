package com.app.jetpack.mvvm.business.artistdetail.data.main.repository

import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {

    override suspend fun artistDetail(artistId: Int): Flow<DataState<ArtistDetail>> {
        return artistRemoteDataSource.artistDetail(artistId)
    }

    override suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> {
        return artistRemoteDataSource.movieCredit(movieId)
    }
}
