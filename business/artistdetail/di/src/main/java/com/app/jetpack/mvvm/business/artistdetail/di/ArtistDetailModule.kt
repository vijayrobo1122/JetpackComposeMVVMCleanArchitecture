package com.app.jetpack.mvvm.business.artistdetail.di

import com.app.jetpack.mvvm.business.artistdetail.data.di.ArtistDetailDataModule
import com.app.jetpack.mvvm.business.artistdetail.domain.di.ArtistDetailDomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ArtistDetailDataModule::class, ArtistDetailDomainModule::class])
@InstallIn(SingletonComponent::class)
object ArtistDetailModule {
    //DO NOTHING
}
