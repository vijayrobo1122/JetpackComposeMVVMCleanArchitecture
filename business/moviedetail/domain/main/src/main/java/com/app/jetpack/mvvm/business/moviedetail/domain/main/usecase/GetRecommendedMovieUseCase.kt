package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.api.IParams
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingFlowParameterized<GetRecommendedMovieUseCase.Params, DataState<BaseModel>> {

    data class Params(
        val movieId: Int,
        val page: Int,
    ) : IParams

    override suspend fun invoke(param: Params): Flow<DataState<BaseModel>> {
        return repository.recommendedMovie(param.movieId, param.page)
    }
}
