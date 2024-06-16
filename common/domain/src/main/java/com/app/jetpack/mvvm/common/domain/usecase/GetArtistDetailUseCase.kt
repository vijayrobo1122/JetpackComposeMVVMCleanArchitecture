package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.DataState
import com.app.jetpack.mvvm.common.domain.UseCase
import com.app.jetpack.mvvm.common.domain.model.artist.ArtistDetail
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetArtistDetailUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlowParameterized<Int, DataState<ArtistDetail>> {

    override suspend fun invoke(param: Int): Flow<DataState<ArtistDetail>> {
        return movieRepository.artistDetail(param)
    }
}
