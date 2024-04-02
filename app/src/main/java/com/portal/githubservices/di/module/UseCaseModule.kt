package com.portal.githubservices.di.module

import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.GithubUseCaseImpl
import com.portal.githubservices.domain.usecase.room.FavoriteUseCase
import com.portal.githubservices.domain.usecase.room.FavoriteUseCaseImpl
import com.portal.githubservices.domain.usecase.room.SearchResultUseCase
import com.portal.githubservices.domain.usecase.room.SearchResultUseCaseImpl
import com.portal.githubservices.domain.usecase.room.UserDetailUseCase
import com.portal.githubservices.domain.usecase.room.UserDetailUseCaseImpl
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
    abstract fun provideSearchResultUseCase(searchResultUseCaseImpl: SearchResultUseCaseImpl): SearchResultUseCase

    @Binds
    abstract fun provideFavoriteUseCase(favoriteUseCaseImpl: FavoriteUseCaseImpl): FavoriteUseCase

    @Binds
    abstract fun provideUserDetailUseCase(userDetailUseCaseImpl: UserDetailUseCaseImpl): UserDetailUseCase
}
