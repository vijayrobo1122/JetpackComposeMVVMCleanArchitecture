package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.api.IParams
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetRecommendedMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlowParameterized<GetRecommendedMovieUseCase.Params, DataState<BaseModel>> {

    data class Params(
        val movieId: Int,
        val page: Int,
    ) : IParams

    override suspend fun invoke(param: Params): Flow<DataState<BaseModel>> {
        return movieRepository.recommendedMovie(param.movieId, param.page)
    }
}
