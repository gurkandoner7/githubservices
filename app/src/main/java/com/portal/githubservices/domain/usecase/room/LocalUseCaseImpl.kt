package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.FavoriteEntity
import com.portal.githubservices.repository.db.LocalRepository
import com.portal.githubservices.repository.db.SearchResultEntity
import com.portal.githubservices.repository.db.UserDetailEntity
import javax.inject.Inject

class LocalUseCaseImpl @Inject constructor(private val localRepository: LocalRepository) :
    LocalUseCase {

    override suspend fun getAllUserDetails(): List<UserDetailEntity> =
        localRepository.getAllUserDetails()

    override suspend fun addUserDetail(userDetailEntity: UserDetailEntity) =
        localRepository.addUserDetail(userDetailEntity)

    override suspend fun addUserDetailList(userDetailEntity: List<UserDetailEntity>) =
        localRepository.addUserDetailList(userDetailEntity)

    override suspend fun deleteAllUserDetails() = localRepository.deleteAllUserDetails()

    override suspend fun deleteAllSearchResults() = localRepository.deleteAllSearchResults()

    override suspend fun addUserSearchResult(searchResultEntityList: List<SearchResultEntity>) =
        localRepository.addUserSearchResult(searchResultEntityList)

    override suspend fun addSearchResult(searchResultEntity: SearchResultEntity) =
        localRepository.addSearchResult(searchResultEntity)

    override suspend fun getAllSearchResults() = localRepository.getAllSearchResults()

    override suspend fun getFavorite() = localRepository.getFavorites()
    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) = localRepository.deleteFavorite(favoriteEntity)
    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        localRepository.addFavorite(favoriteEntity)

    override suspend fun updateFavorite(favoriteEntity: FavoriteEntity) = localRepository.updateFavorite(favoriteEntity)
}