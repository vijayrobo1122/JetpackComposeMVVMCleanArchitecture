package com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote

import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistRemoteDataSourceImpl @Inject constructor(
    private val artistApiService: ArtistApiService,
    private val artistDetailMapper: ArtistDetailMapper,
    private val artistMapper: ArtistMapper,
) : ArtistRemoteDataSource {

    override suspend fun artistDetail(artistId: Int): Flow<DataState<ArtistDetail>> {
        return flow {
            emit(DataState.Loading)
            try {
                val artistDetailResult = artistApiService.artistDetail(artistId)
                emit(DataState.Success(artistDetailMapper.mapTo(artistDetailResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> {
        return flow {
            emit(DataState.Loading)
            try {
                val artistResult = artistApiService.movieCredit(movieId)
                emit(DataState.Success(artistMapper.mapTo(artistResult)))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

}
