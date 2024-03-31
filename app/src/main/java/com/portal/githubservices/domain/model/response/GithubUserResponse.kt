package com.portal.githubservices.domain.model.response

import java.io.Serializable

data class GithubUsersResponse(
    val totalCount: Int? = 0,
    val incompleteResults: Boolean? = false,
    val items: List<GitHubUserInfoResponse> = emptyList()
) : Serializable

data class GitHubUserInfoResponse(
    val id: Long,
    val login: String? = "",
    val avatar_url: String? = "",
) : Serializable