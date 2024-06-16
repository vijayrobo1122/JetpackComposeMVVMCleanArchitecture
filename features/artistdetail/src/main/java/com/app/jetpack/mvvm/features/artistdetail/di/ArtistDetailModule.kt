package com.app.jetpack.mvvm.features.artistdetail.di

import com.app.jetpack.mvvm.common.domain.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.ArtistDetailToUiStateMapper
import com.app.jetpack.mvvm.features.artistdetail.ArtistDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ArtistDetailModule {

    @Provides
    fun provideArtistDetailViewModel(
        getArtistDetailUseCase: GetArtistDetailUseCase,
        artistDetailToUiStateMapper: ArtistDetailToUiStateMapper,
    ): ArtistDetailViewModel {
        return ArtistDetailViewModel(
            getArtistDetailUseCase,
            artistDetailToUiStateMapper
        )
    }
}
