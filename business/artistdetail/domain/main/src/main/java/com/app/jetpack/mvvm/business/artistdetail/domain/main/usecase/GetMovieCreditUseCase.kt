package com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class GetMovieCreditUseCase @Inject constructor(
    private val repository: ArtistRepository
) : UseCase.SuspendingParameterized<Int, Result<Artist>> {

    override suspend fun invoke(param: Int): Result<Artist> {
        return repository.movieCredit(param)
    }
}
