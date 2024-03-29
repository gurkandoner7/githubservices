package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.FavoriteEntity

interface LocalUseCase {
    suspend fun getAllFavorites(): List<FavoriteEntity>

    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    suspend fun addFavoriteList(favoriteEntity: List<FavoriteEntity>)

    suspend fun deleteAllFavorites()
}