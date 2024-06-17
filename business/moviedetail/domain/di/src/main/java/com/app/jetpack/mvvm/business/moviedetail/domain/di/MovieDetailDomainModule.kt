package com.app.jetpack.mvvm.business.moviedetail.domain.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenreMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetNowPlayingMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetPopularMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetTopRatedMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailDomainModule {

    @Provides
    fun provideGetGenreMoviesUseCase(repository: MovieRepository) =
        GetGenreMoviesUseCase(repository)

    @Provides
    fun provideGetGenresListUseCase(repository: MovieRepository) =
        GetGenresListUseCase(repository)

    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository) =
        GetMovieDetailUseCase(repository)

    @Provides
    fun provideGetNowPlayingMoviesUseCase(repository: MovieRepository) =
        GetNowPlayingMoviesUseCase(repository)

    @Provides
    fun provideGetPopularMoviesUseCase(repository: MovieRepository) =
        GetPopularMoviesUseCase(repository)

    @Provides
    fun provideGetRecommendedMovieUseCase(repository: MovieRepository) =
        GetRecommendedMovieUseCase(repository)

    @Provides
    fun provideGetTopRatedMoviesUseCase(repository: MovieRepository) =
        GetTopRatedMoviesUseCase(repository)

    @Provides
    fun provideGetUpcomingMoviesUseCase(repository: MovieRepository) =
        GetUpcomingMoviesUseCase(repository)

}
