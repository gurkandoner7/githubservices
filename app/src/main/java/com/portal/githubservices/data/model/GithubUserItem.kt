package com.portal.githubservices.data.model


data class GithubUserItem(
    val totalCount: Int? = 0,
    val incompleteResults: Boolean? = false,
    val items: List<GitHubUserInfoItem> = emptyList()
)

data class GitHubUserInfoItem(
    val id: Int? = 0,
    val login: String? = "",
    val avatar_url: String? = "",
    var isFavorite: Boolean = false
)