package com.app.jetpack.mvvm.business.artistdetail.domain.di

import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetMovieCreditUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ArtistDetailDomainModule {

    @Provides
    fun provideGetArtistDetailUseCase(repository: ArtistRepository) =
        GetArtistDetailUseCase(repository)

    @Provides
    fun provideGetMovieCreditUseCase(repository: ArtistRepository) =
        GetMovieCreditUseCase(repository)

}
