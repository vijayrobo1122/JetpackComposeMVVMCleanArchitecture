package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.SuspendingFlow<DataState<Genres>> {
    override suspend fun invoke(): Flow<DataState<Genres>> {
        return repository.genreList()
    }
}
