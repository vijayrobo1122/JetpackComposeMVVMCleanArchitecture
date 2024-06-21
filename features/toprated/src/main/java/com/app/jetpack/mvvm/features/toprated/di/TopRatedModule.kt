package com.app.jetpack.mvvm.features.toprated.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetTopRatedMoviesUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.features.toprated.TopRatedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TopRatedModule {

    @Provides
    fun provideTopRatedViewModel(
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
        movieItemToUiStateMapper: MovieItemToUiStateMapper,
    ): TopRatedViewModel {
        return TopRatedViewModel(
            getTopRatedMoviesUseCase,
            movieItemToUiStateMapper,
        )
    }
}
