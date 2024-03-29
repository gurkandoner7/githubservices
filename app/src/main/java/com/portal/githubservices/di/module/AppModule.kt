package com.portal.githubservices.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.portal.githubservices.data.GithubService
import com.portal.githubservices.repository.db.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGithubService(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): GithubService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(GithubService::class.java)

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(application, LocalDatabase::class.java, "local-database")
            .allowMainThreadQueries().build()
    }
}