package com.app.jetpack.mvvm.features.popular.di

import com.app.jetpack.mvvm.common.domain.usecase.GetPopularMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.GenreToUiStateMapper
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
        genreToUiStateMapper: GenreToUiStateMapper,
    ): PopularViewModel {
        return PopularViewModel(
            getPopularMoviesUseCase,
            movieItemToUiStateMapper,
            genreToUiStateMapper
        )
    }
}
