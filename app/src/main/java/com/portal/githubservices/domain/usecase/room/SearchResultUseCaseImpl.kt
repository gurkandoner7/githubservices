package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.LocalRepository
import com.portal.githubservices.repository.db.searchresult.SearchResultEntity
import javax.inject.Inject

class SearchResultUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : SearchResultUseCase {
    override suspend fun deleteAllSearchResults() = localRepository.deleteAllSearchResults()

    override suspend fun addUserSearchResult(searchResultEntityList: List<SearchResultEntity>) =
        localRepository.addUserSearchResult(searchResultEntityList)

    override suspend fun addSearchResult(searchResultEntity: SearchResultEntity) =
        localRepository.addSearchResult(searchResultEntity)

    override suspend fun getAllSearchResults() = localRepository.getAllSearchResults()
}