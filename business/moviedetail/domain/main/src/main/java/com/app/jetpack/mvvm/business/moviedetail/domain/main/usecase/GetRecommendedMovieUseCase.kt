package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.api.IParams
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class GetRecommendedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingParameterized<GetRecommendedMovieUseCase.Params, Result<BaseModel>> {

    data class Params(
        val movieId: Int,
        val page: Int,
    ) : IParams

    override suspend fun invoke(param: Params): Result<BaseModel> {
        return repository.recommendedMovie(param.movieId, param.page)
    }
}
