package com.app.jetpack.mvvm.features.upcoming.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetUpcomingMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.features.upcoming.UpComingViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UpcomingModule {


    @Provides
    fun provideUpComingViewModel(
        getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
        movieItemToUiStateMapper: MovieItemToUiStateMapper,
    ): UpComingViewModel {
        return UpComingViewModel(
            getUpcomingMoviesUseCase,
            movieItemToUiStateMapper,
        )
    }
}
