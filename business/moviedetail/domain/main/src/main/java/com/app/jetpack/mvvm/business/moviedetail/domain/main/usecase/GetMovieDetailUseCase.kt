package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingParameterized<Int, Result<MovieDetail>> {

    override suspend fun invoke(param: Int): Result<MovieDetail> {
        return repository.movieDetail(param)
    }
}
