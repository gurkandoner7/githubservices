package com.portal.githubservices.data.model

data class GithubUserDetailItem(
    val login: String? = "",
    val avatar_url: String? ="",
    val public_repos: String? = "",
    val followers: String? = "",
    val following: String? = "",
    val isFavorite: Boolean = false
)
