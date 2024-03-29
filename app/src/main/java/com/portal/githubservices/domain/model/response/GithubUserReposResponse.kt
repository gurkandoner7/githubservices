package com.portal.githubservices.domain.model.response

import java.io.Serializable

data class GithubUserDetailResponse(
    val login: String? = "",
    val avatar_url: String? ="",
    val public_repos: String? = "",
    val followers: String? = "",
    val following: String? = "",
    var isFavorite: Boolean = false
) : Serializable
