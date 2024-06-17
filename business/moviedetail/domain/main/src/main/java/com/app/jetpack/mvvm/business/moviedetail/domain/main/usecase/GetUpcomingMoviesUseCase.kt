package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import androidx.paging.PagingData
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.api.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.AsyncStreamParameterized<String?, PagingData<MovieItem>> {
    override fun invoke(param: String?): Flow<PagingData<MovieItem>> {
        return repository.upcomingPagingDataSource(param)
    }
}
