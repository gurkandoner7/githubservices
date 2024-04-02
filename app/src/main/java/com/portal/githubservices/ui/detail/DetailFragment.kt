package com.portal.githubservices.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.portal.githubservices.R
import com.portal.githubservices.base.BaseFragment
import com.portal.githubservices.base.viewBinding
import com.portal.githubservices.data.base.NetworkResult
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
    private lateinit var searchedUserItem: GithubUserDetailItem

    override fun observeVariables() {
        lifecycleScope.launch {
            launch {
                detailViewModel.selectedUser.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            detailViewModel.addToUserDetailEntity(result.data.toUserDetailEntity())
                            fillUIComponentsWithResponse(result.data)
                            searchedUserItem = result.data
                            detailViewModel.checkFavoriteState(result.data)
                        }

                        is NetworkResult.Error -> {
                            binding.progressBar.visibility = View.GONE
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("Error")
                                .setMessage(result.message)
                                .setPositiveButton("OK") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .show()
                        }

                        is NetworkResult.Loading -> {
                            if (result.isLoading) {
                                binding.progressBar.visibility = View.VISIBLE
                            } else {
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
            launch {
                detailViewModel.favoriteState.collect { state ->
                    binding.btnFavorite.apply {
                        isChecked = state
                        setOnClickListener {
                            searchedUserItem.let {
                                detailViewModel.updateFavorite(it.toGitHubUserInfoItem(), state)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        detailViewModel.getSelectedUserDetails(arguments?.getString("login") ?: "")
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
        }
    }

}