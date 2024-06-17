package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.api.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingFlowParameterized<String?, PagingData<MovieItem>> {
    override suspend fun invoke(param: String?): Flow<PagingData<MovieItem>> {
        return repository.nowPlayingPagingDataSource(param)
    }
}
