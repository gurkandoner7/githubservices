package com.portal.githubservices.di.module

import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.GithubUseCaseImpl
import com.portal.githubservices.domain.usecase.room.LocalUseCase
import com.portal.githubservices.domain.usecase.room.LocalUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGithubUseCase(githubUseCaseImpl: GithubUseCaseImpl): GithubUseCase

    @Binds
    abstract fun provideLocalUseCase(localUseCaseImpl: LocalUseCaseImpl): LocalUseCase
}
