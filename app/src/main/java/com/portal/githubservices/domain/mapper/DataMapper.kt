package com.portal.githubservices.domain.mapper

import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.model.response.GithubUserDetailResponse
import com.portal.githubservices.domain.model.response.GithubUsersResponse
import com.portal.githubservices.repository.db.UserDetailEntity
import javax.inject.Inject

class DataMapper @Inject constructor()

fun GithubUsersResponse.toGithubUserItem(): GithubUserItem {
    return GithubUserItem(totalCount = totalCount,
        incompleteResults = incompleteResults,
        items = items.map {
            GitHubUserInfoItem(
                id = it.id, login = it.login, avatar_url = it.avatar_url, isFavorite = it.isFavorite
            )
        })
}

fun GithubUserDetailResponse.toGithubUserDetailItem(): GithubUserDetailItem {
    return GithubUserDetailItem(
        id = id,
        login = login,
        avatar_url = avatar_url,
        public_repos = public_repos,
        followers = followers,
        following = following,
        isFavorite = isFavorite    )
}

fun UserDetailEntity.toGithubUserDetailItem(): GithubUserDetailItem {
    return GithubUserDetailItem(
        id = id,
        login = login,
        avatar_url = avatar_url,
        public_repos = public_repos,
        followers = followers,
        following = following,
        isFavorite = isFavorite
    )
}

fun GithubUserDetailItem.toUserDetailEntity(): UserDetailEntity {
    return UserDetailEntity(
        id = id,
        login = login,
        avatar_url = avatar_url,
        public_repos = public_repos,
        followers = followers,
        following = following,
        isFavorite = isFavorite
    )
}
