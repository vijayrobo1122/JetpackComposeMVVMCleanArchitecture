package com.app.jetpack.mvvm.features.popular.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetPopularMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.features.popular.PopularViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PopularModule {

    @Provides
    fun providePopularViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        movieItemToUiStateMapper: MovieItemToUiStateMapper,
    ): PopularViewModel {
        return PopularViewModel(
            getPopularMoviesUseCase,
            movieItemToUiStateMapper,
        )
    }
}
