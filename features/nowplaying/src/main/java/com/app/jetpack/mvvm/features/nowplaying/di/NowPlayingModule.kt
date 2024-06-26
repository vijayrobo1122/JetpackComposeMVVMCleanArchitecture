package com.app.jetpack.mvvm.features.nowplaying.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.FetchLocalGenreListUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetNowPlayingMoviesUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieItemToUiStateMapper
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
        fetchLocalGenreListUseCase: FetchLocalGenreListUseCase,
        genreToUiStateMapper: GenreToUiStateMapper,
    ): NowPlayingViewModel {
        return NowPlayingViewModel(
            getNowPlayingMoviesUseCase,
            movieItemToUiStateMapper,
            fetchLocalGenreListUseCase,
            genreToUiStateMapper,
        )
    }
}
