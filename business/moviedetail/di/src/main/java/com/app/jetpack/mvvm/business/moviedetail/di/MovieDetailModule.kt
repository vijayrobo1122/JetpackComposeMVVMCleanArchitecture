package com.app.jetpack.mvvm.business.moviedetail.di

import com.app.jetpack.mvvm.business.moviedetail.data.di.MovieDetailDataModule
import com.app.jetpack.mvvm.business.moviedetail.domain.di.MovieDetailDomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [MovieDetailDomainModule::class, MovieDetailDataModule::class])
@InstallIn(SingletonComponent::class)
object MovieDetailModule {
    //DO NOTHING
}
