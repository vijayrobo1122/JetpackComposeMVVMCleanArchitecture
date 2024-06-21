package com.app.jetpack.mvvm.di

import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    fun provideMainViewModel(
        getGenresListUseCase: GetGenresListUseCase,
        genreToUiStateMapper: GenreToUiStateMapper,
    ): MainViewModel {
        return MainViewModel(
            getGenresListUseCase,
            genreToUiStateMapper,
        )
    }

}
