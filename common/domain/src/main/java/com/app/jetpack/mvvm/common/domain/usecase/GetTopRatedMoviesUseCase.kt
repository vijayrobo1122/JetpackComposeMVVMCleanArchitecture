package com.app.jetpack.mvvm.common.domain.usecase

import androidx.paging.PagingData
import com.app.jetpack.mvvm.common.domain.UseCase
import com.app.jetpack.mvvm.common.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedMoviesUseCase(
    private val movieRepository: MovieRepository
) : UseCase.AsyncStreamParameterized<String?, PagingData<MovieItem>> {
    override fun invoke(param: String?): Flow<PagingData<MovieItem>> {
        return movieRepository.topRatedPagingDataSource(param)
    }
}
