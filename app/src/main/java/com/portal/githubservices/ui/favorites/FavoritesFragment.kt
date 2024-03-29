package com.portal.githubservices.ui.favorites

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.portal.githubservices.R
import com.portal.githubservices.compose.BaseFragment
import com.portal.githubservices.compose.viewBinding
import com.portal.githubservices.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)

    override fun observeVariables() {
    }

    override fun initUI(savedInstanceState: Bundle?) {
    }


}