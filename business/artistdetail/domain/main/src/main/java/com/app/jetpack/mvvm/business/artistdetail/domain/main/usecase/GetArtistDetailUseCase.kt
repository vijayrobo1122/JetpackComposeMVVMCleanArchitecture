package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(
    private val repository: ArtistRepository
) : UseCase.SuspendingParameterized<Int, Result<ArtistDetail>> {

    override suspend fun invoke(param: Int): Result<ArtistDetail> {
        return repository.artistDetail(param)
    }
}
