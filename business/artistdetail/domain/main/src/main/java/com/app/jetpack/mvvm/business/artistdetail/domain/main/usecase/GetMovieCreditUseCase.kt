package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditUseCase @Inject constructor(
    private val repository: ArtistRepository
) :
    UseCase.SuspendingFlowParameterized<Int, DataState<Artist>> {

    override suspend fun invoke(param: Int): Flow<DataState<Artist>> {
        return repository.movieCredit(param)
    }
}
