package com.app.jetpack.mvvm.features.moviedetail.di

import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.BaseModelToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieDetailToUiStateMapper
import com.app.jetpack.mvvm.features.moviedetail.MovieDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {

    @Provides
    @SuppressWarnings("LongParameterList")
    fun provideMovieDetailViewModel(
        getMovieCreditUseCase: GetMovieCreditUseCase,
        getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
        getMovieDetailUseCase: GetMovieDetailUseCase,
        movieDetailToUiStateMapper: MovieDetailToUiStateMapper,
        artistToUiStateMapper: ArtistToUiStateMapper,
        baseModelToUiStateMapper: BaseModelToUiStateMapper,
    ): MovieDetailViewModel {
        return MovieDetailViewModel(
            getMovieCreditUseCase = getMovieCreditUseCase,
            getRecommendedMovieUseCase = getRecommendedMovieUseCase,
            getMovieDetailUseCase = getMovieDetailUseCase,
            movieDetailToUiStateMapper = movieDetailToUiStateMapper,
            artistToUiStateMapper = artistToUiStateMapper,
            baseModelToUiStateMapper = baseModelToUiStateMapper,
        )
    }
}
