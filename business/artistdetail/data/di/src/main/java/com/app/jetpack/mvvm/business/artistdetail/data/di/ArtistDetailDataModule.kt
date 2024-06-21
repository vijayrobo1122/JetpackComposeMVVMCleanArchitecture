package com.app.jetpack.mvvm.business.artistdetail.data.di

import com.app.jetpack.mvvm.business.artistdetail.data.main.BuildConfig
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.ArtistRemoteDataSource
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote.ArtistApiService
import com.app.jetpack.mvvm.business.artistdetail.data.main.datasource.remote.ArtistRemoteDataSourceImpl
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistDetailMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.mapper.ArtistMapper
import com.app.jetpack.mvvm.business.artistdetail.data.main.repository.ArtistRepositoryImpl
import com.app.jetpack.mvvm.business.artistdetail.domain.main.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArtistDetailDataModule {

    private const val DEFAULT_TIMEOUT = 60L

    @Singleton
    @Provides
    @Named("artist_base_url")
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    /**
     * Provides LoggingInterceptor for api information
     */
    @Singleton
    @Provides
    @Named("artist_logger_interceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    @Named("artist_okhttp")
    fun provideOkHttpClient(
        @Named("artist_logger_interceptor") loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    /**
     * Provides converter factory for retrofit
     */
    @Singleton
    @Provides
    @Named("artist_gson_converted")
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    @Named("artist_retrofit")
    fun provideRetrofitClient(
        @Named("artist_base_url") baseUrl: String,
        @Named("artist_okhttp") okHttpClient: OkHttpClient,
        @Named("artist_gson_converted") converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    /**
     * Provides Api Service using retrofit
     */
    @Singleton
    @Provides
    @Named("artist_api_service")
    fun provideArtistApiService(@Named("artist_retrofit") retrofit: Retrofit): ArtistApiService {
        return retrofit.create(ArtistApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource = artistRemoteDataSource,
        )
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(
        @Named("artist_api_service") artistApiService: ArtistApiService,
        artistDetailMapper: ArtistDetailMapper,
        artistMapper: ArtistMapper,
    ): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(
            artistApiService = artistApiService,
            artistDetailMapper = artistDetailMapper,
            artistMapper = artistMapper,
        )
    }
}
