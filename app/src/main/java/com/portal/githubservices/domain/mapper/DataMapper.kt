package com.portal.githubservices.domain.mapper

import com.portal.githubservices.data.model.GitHubUserInfoItem
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.model.response.GithubUserDetailResponse
import com.portal.githubservices.domain.model.response.GithubUsersResponse
import com.portal.githubservices.repository.db.FavoriteEntity
import com.portal.githubservices.repository.db.SearchResultEntity
import com.portal.githubservices.repository.db.UserDetailEntity
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

fun List<FavoriteEntity>.toGitHubUserInfoList(): List<GitHubUserInfoItem> {
    return map {
        GitHubUserInfoItem(
            id = it.id,
            login = it.login,
            avatar_url = it.avatar_url,
        )
    }
}

fun GitHubUserInfoItem.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id, login, avatar_url
    )
}

fun List<GitHubUserInfoItem>.toSearchResultEntity(): List<SearchResultEntity> {
    val searchResultList = mutableListOf<SearchResultEntity>()
    for (user in this) {
        val searchResult = SearchResultEntity(
            id = user.id,
            avatar_url = user.avatar_url,
            login = user.login,
        )
        searchResultList.add(searchResult)
    }
    return searchResultList
}

fun List<SearchResultEntity>.toGitHubUserInfoItem(): List<GitHubUserInfoItem> {
    val gitHubUserList = mutableListOf<GitHubUserInfoItem>()
    for (searchResult in this) {
        val gitHubUser = GitHubUserInfoItem(
            id = searchResult.id,
            avatar_url = searchResult.avatar_url,
            login = searchResult.login,
        )
        gitHubUserList.add(gitHubUser)
    }
    return gitHubUserList
}


fun GithubUserDetailResponse.toGithubUserDetailItem(): GithubUserDetailItem {
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

fun UserDetailEntity.toGithubUserDetailItem(): GithubUserDetailItem {
    return GithubUserDetailItem(
        id = id,
        login = login,
        avatar_url = avatar_url,
        public_repos = public_repos,
        followers = followers,
        following = following,
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
    )
}

fun GithubUserDetailItem.toGitHubUserInfoItem(): GitHubUserInfoItem {
    return GitHubUserInfoItem(
        id, login, avatar_url
    )
}