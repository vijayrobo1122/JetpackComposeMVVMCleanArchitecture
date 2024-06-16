package com.app.jetpack.mvvm.common.data.di

import com.app.jetpack.mvvm.common.data.datasource.remote.ApiService
import com.app.jetpack.mvvm.common.data.mapper.BaseModelMapper
import com.app.jetpack.mvvm.common.data.mapper.GenresMapper
import com.app.jetpack.mvvm.common.data.mapper.artist.ArtistDetailMapper
import com.app.jetpack.mvvm.common.data.mapper.artist.ArtistMapper
import com.app.jetpack.mvvm.common.data.mapper.moviesdetail.MovieDetailMapper
import com.app.jetpack.mvvm.common.data.repository.MovieRepositoryImpl
import com.app.jetpack.mvvm.common.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService,
        baseModelMapper: BaseModelMapper,
        movieDetailMapper: MovieDetailMapper,
        genresMapper: GenresMapper,
        artistMapper: ArtistMapper,
        artistDetailMapper: ArtistDetailMapper,
    ): MovieRepository {
        return MovieRepositoryImpl(
            apiService,
            baseModelMapper,
            movieDetailMapper,
            genresMapper,
            artistMapper,
            artistDetailMapper
        )
    }

}
