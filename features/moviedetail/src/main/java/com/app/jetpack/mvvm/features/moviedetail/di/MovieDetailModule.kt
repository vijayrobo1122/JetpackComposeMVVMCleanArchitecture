package com.app.jetpack.mvvm.features.moviedetail.di

import com.app.jetpack.mvvm.common.domain.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.ArtistToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.mapper.BaseModelToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieDetailToUiStateMapper
import com.app.jetpack.mvvm.features.moviedetail.MovieDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Provides
    fun provideMovieDetailViewModel(
        getMovieCreditUseCase: GetMovieCreditUseCase,
        getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
        getMovieDetailUseCase: GetMovieDetailUseCase,
        movieDetailToUiStateMapper: MovieDetailToUiStateMapper,
        artistToUiStateMapper: ArtistToUiStateMapper,
        baseModelToUiStateMapper: BaseModelToUiStateMapper,
    ): MovieDetailViewModel {
        return MovieDetailViewModel(
            getMovieCreditUseCase,
            getRecommendedMovieUseCase,
            getMovieDetailUseCase,
            movieDetailToUiStateMapper,
            artistToUiStateMapper,
            baseModelToUiStateMapper,
        )
    }
}
