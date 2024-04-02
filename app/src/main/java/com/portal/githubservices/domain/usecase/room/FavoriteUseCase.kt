package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.favorite.FavoriteEntity

interface FavoriteUseCase {
    suspend fun getFavorite(): List<FavoriteEntity>
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
}