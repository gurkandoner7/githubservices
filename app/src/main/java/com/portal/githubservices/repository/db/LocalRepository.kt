package com.portal.githubservices.repository.db

import androidx.room.Transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject internal constructor(
    localDatabase: LocalDatabase
) {
    private val userDetailDao = localDatabase.userDetailDao()
    private val searchResultDao = localDatabase.searchResultDao()
    private val favoriteDao = localDatabase.favoriteDao()

    fun getAllSearchResults(): List<SearchResultEntity> = searchResultDao.getAllSearchResults()
    fun addSearchResult(searchResultEntity: SearchResultEntity) =
        searchResultDao.addSearchResult(searchResultEntity)

    fun deleteAllSearchResults() = searchResultDao.deleteAllSearchResults()
    fun addUserSearchResult(searchResultEntityList: List<SearchResultEntity>) =
        searchResultDao.addSearchResultList(searchResultEntityList)

    fun getAllUserDetails(): List<UserDetailEntity> = userDetailDao.getAllUserDetails()
    fun addUserDetail(userDetailEntity: UserDetailEntity) =
        userDetailDao.addUserDetail(userDetailEntity)

    fun deleteAllUserDetails() = userDetailDao.deleteAllUserDetails()
    fun addUserDetailList(userDetailEntity: List<UserDetailEntity>) =
        userDetailDao.addUserDetailList(userDetailEntity)

    fun getFavorites(): List<FavoriteEntity> = favoriteDao.getFavorites()
    fun deleteFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.deleteFavorite(favoriteEntity)
    fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)

}


