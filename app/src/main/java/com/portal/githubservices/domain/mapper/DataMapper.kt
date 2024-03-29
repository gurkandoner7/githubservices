package com.portal.githubservices.domain.mapper

import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.model.response.GithubUserDetailResponse
import com.portal.githubservices.domain.model.response.GithubUsersResponse
import javax.inject.Inject

class DataMapper @Inject constructor()

fun GithubUsersResponse.toGithubUserItem(): GithubUserItem {
    return GithubUserItem(
        totalCount = totalCount,
        incompleteResults = incompleteResults,
        items = items.map {
            GitHubUserInfoItem(
                id = it.id, login = it.login, avatar_url = it.avatar_url
            )
        })
}

fun GithubUserDetailResponse.toGithubUserRepoItem(): GithubUserDetailItem {
    return GithubUserDetailItem(
        login, avatar_url, public_repos, followers, followers, isFavorite
    )

}

