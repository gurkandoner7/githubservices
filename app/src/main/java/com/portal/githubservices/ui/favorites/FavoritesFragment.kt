package com.portal.githubservices.ui.favorites

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.portal.githubservices.R
import com.portal.githubservices.compose.BaseFragment
import com.portal.githubservices.compose.viewBinding
import com.portal.githubservices.databinding.FragmentFavoritesBinding
import com.portal.githubservices.domain.mapper.toGitHubUserInfoList
import com.portal.githubservices.ui.search.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private lateinit var adapter: UserListAdapter

    override fun observeVariables() {
        setAdapter()
        lifecycleScope.launch {
            launch {
                favoritesViewModel.favoriteList.collect { listItem ->
                    val list = listItem.toGitHubUserInfoList()
                    if (listItem.isNotEmpty()) {
                        list.map {
                            it.isFavorite = true
                        }
                        adapter.updateItems(list)
                    }
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        favoritesViewModel.getFavorites()
    }

    private fun setAdapter() {
        adapter = UserListAdapter(onItemSelected = { selectedUser ->
            val bundle = Bundle().apply {
                putString("login", selectedUser.login)
            }
            view?.findNavController()
                ?.navigate(R.id.action_navigation_favorites_to_navigation_detail, bundle)
        }, onFavoriteStateChanged = { item, state ->
            favoritesViewModel.updateFavorite(item, state)
            if (state) adapter.removeItem(item)
        })
        binding.rvUserList.adapter = adapter
    }


}