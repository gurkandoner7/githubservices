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
import com.portal.githubservices.domain.usecase.room.FavoriteUseCase
import com.portal.githubservices.domain.usecase.room.UserDetailUseCase
import com.portal.githubservices.repository.db.userdetail.UserDetailEntity
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
    private val favoriteUseCase: FavoriteUseCase,
    private val userDetailUseCase: UserDetailUseCase
) : ViewModel() {

    private val _selectedUser = MutableSharedFlow<NetworkResult<GithubUserDetailItem>>()
    val selectedUser = _selectedUser.asSharedFlow()

    private val _favoriteState = MutableStateFlow<Boolean>(false)
    val favoriteState = _favoriteState.asStateFlow()


    fun getSelectedUserDetails(
        user: String
    ) {
        viewModelScope.launch {
            _selectedUser.emit(NetworkResult.Loading(isLoading = true))
            githubUseCase.getUserDetails(user = user).onSuccess {
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
            userDetailUseCase.addUserDetail(userDetailEntity)
        }
    }

    fun updateFavorite(item: GitHubUserInfoItem, state: Boolean) {
        if (state) {
            viewModelScope.launch {
                favoriteUseCase.deleteFavorite(item.toFavoriteEntity())
                item.isFavorite = false
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                favoriteUseCase.addFavorite(item.toFavoriteEntity())
                item.isFavorite = true
            }
        }
    }

    fun checkFavoriteState(response: GithubUserDetailItem) {
        viewModelScope.launch {
            _favoriteState.value = favoriteUseCase.getFavorite().any {
                it.id == response.id
            }
        }
    }
}