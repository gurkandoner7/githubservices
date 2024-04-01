package com.portal.githubservices.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.base.NetworkResult
import com.portal.githubservices.data.base.onError
import com.portal.githubservices.data.base.onSuccess
import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.mapper.toFavoriteEntity
import com.portal.githubservices.domain.mapper.toGitHubUserInfoItem
import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.room.LocalUseCase
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
    private val useCase: GithubUseCase, private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _searchedUser = MutableSharedFlow<NetworkResult<GithubUserItem>>()
    val searchedUser = _searchedUser.asSharedFlow()

    private val _searchedUserFromCache = MutableStateFlow<List<GitHubUserInfoItem>>(emptyList())
    val searchedUserFromCache = _searchedUserFromCache.asStateFlow()

    fun getSearchUserList(query: String?) {
        viewModelScope.launch {
            _searchedUser.emit(NetworkResult.Loading(isLoading = true))
            localUseCase.deleteAllSearchResults()
            query?.let { query ->
                useCase.getSearchUser(query,1,50).onSuccess {result->
                    _searchedUser.emit(NetworkResult.Loading(isLoading = false))
                    _searchedUser.emit(NetworkResult.Success(result))
                }.onError { errorMessage ->
                    _searchedUser.emit(NetworkResult.Loading(isLoading = false))
                    _searchedUser.emit(NetworkResult.Error(errorMessage))
                }
            }
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

    fun updateFavorite(item: GitHubUserInfoItem, state: Boolean) {
        if (state) {
            viewModelScope.launch {
                localUseCase.deleteFavorite(item.toFavoriteEntity())
                item.isFavorite = false
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                localUseCase.addFavorite(item.toFavoriteEntity())
                item.isFavorite = true
            }
        }
    }

    fun checkFavorites(list: List<GitHubUserInfoItem>) {
        viewModelScope.launch {
            list.map {
                it.isFavorite = false
            }
            val matchingFavorites = list.filter { searchedItem ->
                localUseCase.getFavorite().map { favoriteItem ->
                    favoriteItem.id
                }.contains(searchedItem.id)
            }
            matchingFavorites.forEach {
                it.isFavorite = true
            }
        }
    }


}