package com.app.jetpack.mvvm.common.data.di

import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import com.app.jetpack.mvvm.common.domain.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetGenreMoviesUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetNowPlayingMoviesUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetPopularMoviesUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetTopRatedMoviesUseCase
import com.app.jetpack.mvvm.common.domain.usecase.GetUpcomingMoviesUseCase
import com.app.jetpack.mvvm.common.domain.usecase.SearchMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetCharactersUseCase(repository: MovieRepository): GetArtistDetailUseCase {
        return GetArtistDetailUseCase(repository)
    }

    @Provides
    fun provideGetGenresListUseCase(repository: MovieRepository): GetGenresListUseCase {
        return GetGenresListUseCase(repository)
    }

    @Provides
    fun provideGetGenreMoviesUseCase(repository: MovieRepository): GetGenreMoviesUseCase {
        return GetGenreMoviesUseCase(repository)
    }

    @Provides
    fun provideGetMovieCreditUseCase(repository: MovieRepository): GetMovieCreditUseCase {
        return GetMovieCreditUseCase(repository)
    }

    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(repository)
    }

    @Provides
    fun provideGetNowPlayingMoviesUseCase(repository: MovieRepository): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(repository)
    }

    @Provides
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository)
    }

    @Provides
    fun provideGetRecommendedMovieUseCase(repository: MovieRepository): GetRecommendedMovieUseCase {
        return GetRecommendedMovieUseCase(repository)
    }

    @Provides
    fun provideGetTopRatedMoviesUseCase(repository: MovieRepository): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(repository)
    }

    @Provides
    fun provideGetUpcomingMoviesUseCase(repository: MovieRepository): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(repository)
    }

    @Provides
    fun provideSearchMovieUseCase(repository: MovieRepository): SearchMovieUseCase {
        return SearchMovieUseCase(repository)
    }
}
