package com.portal.githubservices.repository.db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject internal constructor(
    localDatabase: LocalDatabase
) {
    private val favoriteDao = localDatabase.favoriteDao()

    fun getAllFavorites(): List<FavoriteEntity> = favoriteDao.getAllFavorites()

    fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)

    fun deleteAllFavorites() = favoriteDao.deleteAllFavorites()

    fun addFavoriteList(favoriteEntity: List<FavoriteEntity>) =
        favoriteDao.addFavoriteList(favoriteEntity)
}