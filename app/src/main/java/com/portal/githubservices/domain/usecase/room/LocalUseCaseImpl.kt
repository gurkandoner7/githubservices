package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.FavoriteEntity
import com.portal.githubservices.repository.db.LocalRepository
import javax.inject.Inject

class LocalUseCaseImpl @Inject constructor(private val localRepository: LocalRepository) :
    LocalUseCase {
    override suspend fun getAllFavorites(): List<FavoriteEntity> =
        localRepository.getAllFavorites()

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.addFavorite(favoriteEntity)

    override suspend fun addFavoriteList(favoriteEntity: List<FavoriteEntity>) =
        localRepository.addFavoriteList(favoriteEntity)

    override suspend fun deleteAllFavorites() = localRepository.deleteAllFavorites()
}