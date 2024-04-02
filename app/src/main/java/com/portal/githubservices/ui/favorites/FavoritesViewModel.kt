package com.portal.githubservices.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.domain.mapper.toFavoriteEntity
import com.portal.githubservices.domain.usecase.room.FavoriteUseCase
import com.portal.githubservices.repository.db.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    ViewModel() {

    private val _favoriteList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()


    fun getFavorites() {
        viewModelScope.launch {
            _favoriteList.value = favoriteUseCase.getFavorite()
        }
    }

    fun updateFavorite(item: GitHubUserInfoItem, state: Boolean) {
        if (state) {
            viewModelScope.launch {
                favoriteUseCase.deleteFavorite(item.toFavoriteEntity())
                item.isFavorite = false
                getFavorites()
            }
        } else {
            viewModelScope.launch {
                favoriteUseCase.addFavorite(item.toFavoriteEntity())
                item.isFavorite = true
                getFavorites()
            }
        }
    }

}