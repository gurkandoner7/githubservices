package com.portal.githubservices.di.module

import com.portal.githubservices.domain.repository.GithubRepository
import com.portal.githubservices.domain.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository
}