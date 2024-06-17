package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(
    private val repository: ArtistRepository
) : UseCase.SuspendingFlowParameterized<Int, DataState<ArtistDetail>> {

    override suspend fun invoke(param: Int): Flow<DataState<ArtistDetail>> {
        return repository.artistDetail(param)
    }
}
