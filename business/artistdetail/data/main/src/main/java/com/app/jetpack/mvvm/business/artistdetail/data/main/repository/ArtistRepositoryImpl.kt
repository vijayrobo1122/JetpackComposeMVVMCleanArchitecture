package com.app.jetpack.mvvm.business.artistdetail.data.main.repository

import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistDetailMapper: ArtistDetailMapper,
    private val artistMapper: ArtistMapper,
) : ArtistRepository {

    override suspend fun artistDetail(artistId: Int): Result<ArtistDetail> {
        return artistRemoteDataSource.artistDetail(artistId).map { artistDetailMapper.mapTo(it) }
    }

    override suspend fun movieCredit(movieId: Int): Result<Artist> {
        return artistRemoteDataSource.movieCredit(movieId).map { artistMapper.mapTo(it) }
    }
}
