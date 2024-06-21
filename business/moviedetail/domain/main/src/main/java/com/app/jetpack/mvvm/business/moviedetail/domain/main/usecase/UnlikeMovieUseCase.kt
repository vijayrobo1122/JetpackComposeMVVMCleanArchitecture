package com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.common.domain.api.UseCase
import javax.inject.Inject

class UnlikeMovieUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase.SuspendingParameterized<Int, Unit> {

    override suspend fun invoke(param: Int) {
        repository.unlikeMovie(param)
    }
}
