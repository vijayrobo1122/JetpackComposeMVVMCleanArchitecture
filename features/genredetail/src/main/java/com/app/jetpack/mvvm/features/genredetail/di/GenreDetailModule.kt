package com.app.jetpack.mvvm.features.genredetail.di

import com.app.jetpack.mvvm.common.domain.usecase.GetGenreMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.features.genredetail.GenreDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreDetailModule {

    @Provides
    fun provideGenreViewModel(
        getGenreMoviesUseCase: GetGenreMoviesUseCase,
        movieItemToUiStateMapper: MovieItemToUiStateMapper,
    ): GenreDetailViewModel {
        return GenreDetailViewModel(
            getGenreMoviesUseCase,
            movieItemToUiStateMapper,
        )
    }
}
