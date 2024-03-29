package com.portal.githubservices.di.module

import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.GithubUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGithubUseCase(githubUseCaseImpl: GithubUseCaseImpl): GithubUseCase
}
