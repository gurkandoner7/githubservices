package com.portal.githubservices.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: GithubUseCase) : ViewModel() {

    private val _selectedUser = MutableSharedFlow<GithubUserDetailItem>()
    val selectedUser = _selectedUser.asSharedFlow()

    fun getSelectedUserRepos(
        user: String
    ) {
        viewModelScope.launch {
            try {
                _selectedUser.emit(useCase.getUserRepositories(user = user))
            } catch (e: Exception) {
                e
            }

        }

    }


}