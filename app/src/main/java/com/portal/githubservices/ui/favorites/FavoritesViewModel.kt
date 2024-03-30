package com.portal.githubservices.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portal.githubservices.domain.usecase.room.LocalUseCase
import com.portal.githubservices.repository.db.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val localUseCase: LocalUseCase) : ViewModel() {

    private val _favoriteList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    fun updateFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            localUseCase.updateFavorite(favoriteEntity)
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            _favoriteList.value = localUseCase.getFavorite()
        }
    }
}