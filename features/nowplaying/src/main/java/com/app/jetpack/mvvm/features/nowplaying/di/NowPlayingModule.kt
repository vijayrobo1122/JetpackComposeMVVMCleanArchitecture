package com.app.jetpack.mvvm.features.nowplaying.di

import com.app.jetpack.mvvm.common.domain.usecase.GetNowPlayingMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.features.nowplaying.NowPlayingViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NowPlayingModule {

    @Provides
    fun provideNowPlayingViewModel(
        getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
        movieItemToUiStateMapper: MovieItemToUiStateMapper,
    ): NowPlayingViewModel {
        return NowPlayingViewModel(
            getNowPlayingMoviesUseCase,
            movieItemToUiStateMapper,
        )
    }
}
