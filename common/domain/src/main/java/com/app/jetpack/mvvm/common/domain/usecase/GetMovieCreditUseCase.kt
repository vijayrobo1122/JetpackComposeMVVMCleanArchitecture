package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.DataState
import com.app.jetpack.mvvm.common.domain.UseCase
import com.app.jetpack.mvvm.common.domain.model.artist.Artist
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieCreditUseCase(private val movieRepository: MovieRepository) :
    UseCase.SuspendingFlowParameterized<Int, DataState<Artist>> {
    override suspend fun invoke(param: Int): Flow<DataState<Artist>> {
        return movieRepository.movieCredit(param)
    }
}
