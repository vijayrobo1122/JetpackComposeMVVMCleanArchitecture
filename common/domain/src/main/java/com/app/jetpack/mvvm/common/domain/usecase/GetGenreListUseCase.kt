package com.app.jetpack.mvvm.common.domain.usecase

import com.app.jetpack.mvvm.common.domain.api.UseCase
import com.app.jetpack.mvvm.common.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetGenresListUseCase(
    private val movieRepository: MovieRepository
) : UseCase.SuspendingFlow<DataState<Genres>> {
    override suspend fun invoke(): Flow<DataState<Genres>> {
        return movieRepository.genreList()
    }
}
