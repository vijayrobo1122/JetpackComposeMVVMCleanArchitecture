package com.app.jetpack.mvvm.common.domain.usecase

import androidx.paging.PagingData
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlowParameterized<String?, PagingData<MovieItem>> {
    override suspend fun invoke(param: String?): Flow<PagingData<MovieItem>> {
        return movieRepository.popularPagingDataSource(param)
    }
}
