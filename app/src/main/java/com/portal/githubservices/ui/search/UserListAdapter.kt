package com.portal.githubservices.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portal.githubservices.R
import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.databinding.UserListItemBinding
import com.portal.githubservices.utilities.extensions.loadImageWithUrlAndPlaceHolder

@SuppressLint("NotifyDataSetChanged")
class UserListAdapter(
    private val onItemSelected: (GitHubUserInfoItem) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var items = mutableListOf<GitHubUserInfoItem>()

    inner class UserListViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GitHubUserInfoItem) {
            binding.apply {
                tvUserName.text = item.login
                clUser.setOnClickListener {
                    onItemSelected(item)
                }
                ivAvatar.loadImageWithUrlAndPlaceHolder(
                    item.avatar_url, R.drawable.ic_notifications_black_24dp
                )
                btnFavorite.setOnClickListener {
                    item.isFavorite = !item.isFavorite
                    btnFavorite.isActivated = item.isFavorite
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): UserListAdapter.UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<GitHubUserInfoItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}