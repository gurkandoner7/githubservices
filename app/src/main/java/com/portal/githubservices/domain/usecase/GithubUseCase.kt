package com.portal.githubservices.domain.usecase

import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem

interface GithubUseCase {
    suspend fun getSearchUser(
        searchKeyword: String,
        page: Int,
        perPage: Int
    ): GithubUserItem


    suspend fun getUserRepositories(user: String): GithubUserDetailItem
}