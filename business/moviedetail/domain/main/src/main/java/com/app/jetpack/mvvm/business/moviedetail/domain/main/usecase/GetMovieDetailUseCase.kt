package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingFlowParameterized<Int, DataState<MovieDetail>> {

    override suspend fun invoke(param: Int): Flow<DataState<MovieDetail>> {
        return repository.movieDetail(param)
    }
}
