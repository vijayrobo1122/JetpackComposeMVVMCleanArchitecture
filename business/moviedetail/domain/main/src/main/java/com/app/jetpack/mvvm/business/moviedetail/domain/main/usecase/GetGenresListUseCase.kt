package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.Suspending<Result<Genres>> {
    override suspend fun invoke(): Result<Genres> {
        return repository.genreList()
    }
}
