package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.DataState
import com.app.jetpack.mvvm.common.domain.UseCase
import com.app.jetpack.mvvm.common.domain.model.moviedetail.MovieDetail
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlowParameterized<Int, DataState<MovieDetail>> {

    override suspend fun invoke(param: Int): Flow<DataState<MovieDetail>> {
        return movieRepository.movieDetail(param)
    }

}
