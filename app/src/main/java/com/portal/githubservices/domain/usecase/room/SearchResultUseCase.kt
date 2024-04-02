package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.searchresult.SearchResultEntity

interface SearchResultUseCase {
    suspend fun deleteAllSearchResults()
    suspend fun addUserSearchResult(searchResultEntityList: List<SearchResultEntity>)
    suspend fun addSearchResult(searchResultEntity: SearchResultEntity)
    suspend fun getAllSearchResults(): List<SearchResultEntity>
}