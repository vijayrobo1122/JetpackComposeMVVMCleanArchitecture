package com.app.jetpack.mvvm.business.moviedetail.data.di

import android.app.Application
import androidx.room.Room
import com.app.jetpack.mvvm.business.moviedetail.data.main.BuildConfig
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieRemoteDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.MovieLocalDataSourceImpl
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.database.FavoriteAppDatabase
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieApiService
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.remote.MovieRemoteDataSourceImpl
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.BaseModelMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.GenresMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.mapper.MovieDetailMapper
import com.app.jetpack.mvvm.business.moviedetail.data.main.repository.MovieRepositoryImpl
import com.app.jetpack.mvvm.business.moviedetail.domain.main.repository.MovieRepository
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

@SuppressWarnings("TooManyFunctions")
@Module
@InstallIn(SingletonComponent::class)
object MovieDetailDataModule {

    private const val DEFAULT_TIMEOUT = 60L

    /**
     * Provides BaseUrl as string
     */
    @Singleton
    @Provides
    @Named("movie_base_url")
    fun provideMovieBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    /**
     * Provides LoggingInterceptor for api information
     */
    @Singleton
    @Provides
    @Named("movie_logger_interceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    @Named("movie_okhttp")
    fun provideOkHttpClient(@Named("movie_logger_interceptor") loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
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
    @Named("movie_gson_converted")
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    @Named("movie_retrofit")
    fun provideRetrofitClient(
        @Named("movie_base_url") baseUrl: String,
        @Named("movie_okhttp") okHttpClient: OkHttpClient,
        @Named("movie_gson_converted") converterFactory: Converter.Factory
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
    @Named("movie_api_service")
    fun provideArtistApiService(@Named("movie_retrofit") retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        movieLocalDataSource: MovieLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource,
        baseModelMapper: BaseModelMapper,
        movieDetailMapper: MovieDetailMapper,
        genresMapper: GenresMapper,
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieLocalDataSource = movieLocalDataSource,
            movieRemoteDataSource = movieRemoteDataSource,
            baseModelMapper = baseModelMapper,
            movieDetailMapper = movieDetailMapper,
            genresMapper = genresMapper,
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteAppDatabase(app: Application): FavoriteAppDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteAppDatabase::class.java,
            FavoriteAppDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    fun provideFavoriteDao(database: FavoriteAppDatabase): FavoriteDao = database.favoriteDao()

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(
        favoriteDao: FavoriteDao
    ): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(favoriteDao = favoriteDao)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        @Named("movie_api_service") apiService: MovieApiService,
        baseModelMapper: BaseModelMapper,
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(
            apiService = apiService,
            baseModelMapper = baseModelMapper,
        )
    }

}
