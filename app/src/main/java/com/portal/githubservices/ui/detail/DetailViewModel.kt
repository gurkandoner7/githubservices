package com.portal.githubservices.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.base.NetworkResult
import com.portal.githubservices.data.base.onError
import com.portal.githubservices.data.base.onSuccess
import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.domain.mapper.toFavoriteEntity
import com.portal.githubservices.domain.usecase.GithubUseCase
import com.portal.githubservices.domain.usecase.room.LocalUseCase
import com.portal.githubservices.repository.db.UserDetailEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _selectedUser = MutableSharedFlow<NetworkResult<GithubUserDetailItem>>()
    val selectedUser = _selectedUser.asSharedFlow()

    private val _favoriteState = MutableStateFlow<Boolean>(false)
    val favoriteState = _favoriteState.asStateFlow()


    fun getSelectedUserRepos(
        user: String
    ) {
        viewModelScope.launch {
            _selectedUser.emit(NetworkResult.Loading(isLoading = true))
            githubUseCase.getUserRepositories(user = user).onSuccess {
                _selectedUser.emit(NetworkResult.Loading(isLoading = false))
                _selectedUser.emit(NetworkResult.Success(it))
            }.onError { message ->
                _selectedUser.emit(NetworkResult.Loading(isLoading = true))
                _selectedUser.emit(NetworkResult.Error(message))
            }
        }
    }

    fun addToUserDetailEntity(userDetailEntity: UserDetailEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            localUseCase.addUserDetail(userDetailEntity)
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

    fun checkFavoriteState(response: GithubUserDetailItem) {
        viewModelScope.launch {
            _favoriteState.value = localUseCase.getFavorite().any {
                it.id == response.id
            }
        }
    }
}