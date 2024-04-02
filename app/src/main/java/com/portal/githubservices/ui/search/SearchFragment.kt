package com.portal.githubservices.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.portal.githubservices.R
import com.portal.githubservices.base.BaseFragment
import com.portal.githubservices.base.viewBinding
import com.portal.githubservices.data.base.NetworkResult
import com.portal.githubservices.databinding.FragmentSearchBinding
import com.portal.githubservices.domain.mapper.toSearchResultEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModels()
    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private lateinit var adapter: UserListAdapter

    override fun observeVariables() {
        lifecycleScope.launch {
            launch {
                searchViewModel.searchedUser.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            searchViewModel.checkFavorites(result.data.items)
                            searchViewModel.addSearchResult(result.data.items.toSearchResultEntity())
                            adapter.updateItems(result.data.items)
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
                                binding.rvUserList.visibility = View.GONE
                                binding.progressBar.visibility = View.VISIBLE
                            } else {
                                binding.rvUserList.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
            launch {
                searchViewModel.searchedUserFromCache.collect { cacheList ->
                    searchViewModel.checkFavorites(cacheList)
                    adapter.updateItems(cacheList)
                }
            }
        }
    }


    override fun initUI(savedInstanceState: Bundle?) {
        setAdapter()
        binding.rvUserList.adapter = adapter
        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        searchViewModel.getSearchUserList(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        searchViewModel.getSearchResultListFromDb()
    }

    private fun setAdapter() {
        adapter = UserListAdapter(onItemSelected = { selectedUser ->
            val bundle = Bundle().apply {
                putString("login", selectedUser.login)
            }
            view?.findNavController()
                ?.navigate(R.id.action_navigation_search_to_navigation_detail, bundle)

        }, onFavoriteStateChanged = { item, state ->
            searchViewModel.updateFavorite(item, state)
        }, onListItemSize = { count ->
            if (count == 0) {
                binding.tvSearchListEmpty.visibility = View.VISIBLE
            } else binding.tvSearchListEmpty.visibility = View.GONE
        })
    }
}