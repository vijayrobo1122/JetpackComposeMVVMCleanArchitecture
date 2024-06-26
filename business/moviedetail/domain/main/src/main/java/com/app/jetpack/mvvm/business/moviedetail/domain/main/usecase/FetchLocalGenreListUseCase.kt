package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class FetchLocalGenreListUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase.Suspending<List<Genre>> {
    override suspend fun invoke(): List<Genre> {
        return repository.fetchLocalGenreList()
    }
}
