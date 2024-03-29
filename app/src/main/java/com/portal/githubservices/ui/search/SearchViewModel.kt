package com.portal.githubservices.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GithubUseCase
) : ViewModel() {

    private val _searchedUser = MutableSharedFlow<GithubUserItem>()
    val searchedUser = _searchedUser.asSharedFlow()
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun getSearchUserList(query: String?) {
        viewModelScope.launch {
            try {
                val response = query?.let { useCase.getSearchUser(it, 3, 30) }
                response?.let { _searchedUser.emit(it) }
            } catch (e: Exception) {
                _errorFlow.emit("qweqweqwe")
            }
        }
    }

}