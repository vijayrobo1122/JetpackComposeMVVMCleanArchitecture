package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.model.BaseModel
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlowParameterized<String, DataState<BaseModel>> {
    override suspend fun invoke(param: String): Flow<DataState<BaseModel>> {
        return movieRepository.search(param)
    }
}
