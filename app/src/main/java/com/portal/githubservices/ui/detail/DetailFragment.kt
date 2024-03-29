package com.portal.githubservices.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.portal.githubservices.R
import com.portal.githubservices.compose.BaseFragment
import com.portal.githubservices.compose.viewBinding
import com.portal.githubservices.databinding.FragmentDetailBinding
import com.portal.githubservices.domain.mapper.toUserDetailEntity
import com.portal.githubservices.utilities.extensions.loadImageWithUrlAndPlaceHolder
import com.portal.githubservices.utilities.helper.Util.Companion.MAGIC_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val detailViewModel: DetailViewModel by activityViewModels()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)

    override fun observeVariables() {
        lifecycleScope.launch {
            launch {
                detailViewModel.selectedUser.collect { response ->
                    detailViewModel.addToUserDetailEntity(response.toUserDetailEntity())
                    binding.apply {
                        tvDetailUserName.text = response.login
                        ivAvatar.loadImageWithUrlAndPlaceHolder(
                            response.avatar_url, R.drawable.ic_notifications_black_24dp
                        )
                        tvFollowers.text = response.followers?.let { followersCount ->
                            getString(R.string.followers).replace(
                                MAGIC_KEY, followersCount
                            )
                        }
                        tvFollowing.text = response.following?.let { followingCount ->
                            getString(R.string.following).replace(MAGIC_KEY, followingCount)
                        }
                        tvPublicRepoCount.text = response.public_repos?.let { repoCount ->
                            getString(R.string.repository_amount).replace(
                                MAGIC_KEY, repoCount
                            )
                        }
                    }

                }
            }
            launch {
                detailViewModel.getAllUserDetails.collect { entityResponse ->
                    entityResponse.forEach { detailEntity ->
                        Log.d("DetailFragment", detailEntity.toString())
                    }
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        detailViewModel.getSelectedUserRepos(arguments?.getString("login") ?: "null")
        binding.testButton.setOnClickListener {
            detailViewModel.getAllUserDetails()
        }
    }

}