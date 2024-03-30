package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.FavoriteEntity
import com.portal.githubservices.repository.db.SearchResultEntity
import com.portal.githubservices.repository.db.UserDetailEntity

interface LocalUseCase {
    suspend fun getAllUserDetails(): List<UserDetailEntity>
    suspend fun addUserDetail(userDetailEntity: UserDetailEntity)
    suspend fun addUserDetailList(userDetailEntity: List<UserDetailEntity>)
    suspend fun deleteAllUserDetails()
    suspend fun getAllSearchResults(): List<SearchResultEntity>
    suspend fun addSearchResult(searchResultEntity: SearchResultEntity)
    suspend fun addUserSearchResult(searchResultEntityList: List<SearchResultEntity>)
    suspend fun deleteAllSearchResults()
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
    suspend fun getFavorite(): List<FavoriteEntity>
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
    suspend fun updateFavorite(favoriteEntity: FavoriteEntity)
}