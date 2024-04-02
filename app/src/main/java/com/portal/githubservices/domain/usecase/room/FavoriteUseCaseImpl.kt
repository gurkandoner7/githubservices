package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.favorite.FavoriteEntity
import com.portal.githubservices.repository.db.LocalRepository
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : FavoriteUseCase {

    override suspend fun getFavorite() = localRepository.getFavorites()
    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.deleteFavorite(favoriteEntity)

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.addFavorite(favoriteEntity)

}