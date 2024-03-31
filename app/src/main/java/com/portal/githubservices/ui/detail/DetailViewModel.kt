package com.portal.githubservices.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.model.GithubUserDetailItem
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

    private val _selectedUser = MutableSharedFlow<GithubUserDetailItem>()
    val selectedUser = _selectedUser.asSharedFlow()

    private val _getAllUserDetails = MutableStateFlow<List<UserDetailEntity>>(emptyList())
    val getAllUserDetails = _getAllUserDetails.asStateFlow()

    private val _favoriteState = MutableStateFlow<Boolean>(false)
    val favoriteState = _favoriteState.asStateFlow()

    fun getSelectedUserRepos(
        user: String
    ) {
        viewModelScope.launch {
            try {
                _selectedUser.emit(githubUseCase.getUserRepositories(user = user))
            } catch (e: Exception) {
                e
            }
        }
    }

    fun getAllUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _getAllUserDetails.value = localUseCase.getAllUserDetails()
        }
    }

    fun addToUserDetailEntity(userDetailEntity: UserDetailEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            localUseCase.addUserDetail(userDetailEntity)
        }
    }


    fun checkFavoriteState(response: GithubUserDetailItem): Boolean {
        viewModelScope.launch {
            _favoriteState.value = localUseCase.getFavorite().any {
                it.id == response.id
            }
        }
        return favoriteState.value
    }
}