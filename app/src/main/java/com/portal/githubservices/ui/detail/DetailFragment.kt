package com.portal.githubservices.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.portal.githubservices.R
import com.portal.githubservices.compose.BaseFragment
import com.portal.githubservices.compose.viewBinding
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.databinding.FragmentDetailBinding
import com.portal.githubservices.domain.mapper.toGitHubUserInfoItem
import com.portal.githubservices.domain.mapper.toUserDetailEntity
import com.portal.githubservices.utilities.extensions.loadImageWithUrlAndPlaceHolder
import com.portal.githubservices.utilities.helper.Util.Companion.MAGIC_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val detailViewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)

    override fun observeVariables() {
        lifecycleScope.launch {
            launch {
                detailViewModel.selectedUser.collect { response ->
                    detailViewModel.addToUserDetailEntity(response.toUserDetailEntity())
                    fillUIComponentsWithResponse(response)
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        detailViewModel.getSelectedUserRepos(arguments?.getString("login") ?: "")
    }

    private fun fillUIComponentsWithResponse(detailResponse: GithubUserDetailItem) {
        binding.apply {
            tvDetailUserName.text = detailResponse.login
            ivAvatar.loadImageWithUrlAndPlaceHolder(
                detailResponse.avatar_url, R.drawable.ic_dummy_profile
            )
            tvFollowers.text = detailResponse.followers?.let { followersCount ->
                getString(R.string.followers).replace(
                    MAGIC_KEY, followersCount
                )
            }
            tvFollowing.text = detailResponse.following?.let { followingCount ->
                getString(R.string.following).replace(MAGIC_KEY, followingCount)
            }
            tvPublicRepoCount.text = detailResponse.public_repos?.let { repoCount ->
                getString(R.string.repository_amount).replace(
                    MAGIC_KEY, repoCount
                )
            }
            binding.ivFavorite.apply {
                isActivated = detailViewModel.checkFavoriteState(detailResponse)
                setOnClickListener {
                    isActivated = !isActivated
                    detailViewModel.updateFavorite(
                        detailResponse.toGitHubUserInfoItem(),
                        detailViewModel.favoriteState.value
                    )
                }
            }
        }
    }

}