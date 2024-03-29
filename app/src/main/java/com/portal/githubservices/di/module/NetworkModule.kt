package com.portal.githubservices.di.module

import com.google.gson.GsonBuilder
import com.portal.githubservices.data.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(45L, TimeUnit.SECONDS)
            .writeTimeout(45L, TimeUnit.SECONDS)
            .readTimeout(45L, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun provideBaseUrl() = GithubService.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            )
        )
    }

}