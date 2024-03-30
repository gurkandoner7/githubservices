package com.portal.githubservices.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.mapper.toGitHubUserInfoItem
import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.room.LocalUseCase
import com.portal.githubservices.repository.db.FavoriteEntity
import com.portal.githubservices.repository.db.SearchResultEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GithubUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _searchedUser = MutableSharedFlow<GithubUserItem>()
    val searchedUser = _searchedUser.asSharedFlow()

    private val _searchedUserFromCache = MutableStateFlow<List<GitHubUserInfoItem>>(emptyList())
    val searchedUserFromCache = _searchedUserFromCache.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun getSearchUserList(query: String?) {
        viewModelScope.launch {
            try {
                localUseCase.deleteAllSearchResults()
                val response = query?.let { useCase.getSearchUser(it, 3, 30) }
                response?.let { _searchedUser.emit(it) }
            } catch (e: Exception) {
                _errorFlow.emit("qweqweqwe")
            }
        }
    }


    fun updateFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            localUseCase.updateFavorite(favoriteEntity)
        }
    }

    fun addSearchResult(resultList: List<SearchResultEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            localUseCase.addUserSearchResult(resultList)
        }
    }

    fun getSearchResultListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            _searchedUserFromCache.value = localUseCase.getAllSearchResults().toGitHubUserInfoItem()
        }
    }


}