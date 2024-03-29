package com.portal.githubservices.ui.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.portal.githubservices.R
import com.portal.githubservices.compose.BaseFragment
import com.portal.githubservices.compose.viewBinding
import com.portal.githubservices.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private lateinit var adapter: UserListAdapter

    override fun observeVariables() {
        lifecycleScope.launch {
            launch {
                searchViewModel.searchedUser.collect {
                    adapter.updateItems(it.items)

                }
            }
        }
    }


    override fun initUI(savedInstanceState: Bundle?) {
        setAdapter()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun setAdapter() {
        adapter = UserListAdapter(onItemSelected = { selectedUser ->
            val bundle = Bundle().apply {
                putString("login", selectedUser.login)
            }
            view?.findNavController()
                ?.navigate(R.id.action_navigation_search_to_navigation_detail, bundle)
        })
        binding.rvUserList.adapter = adapter
    }

}