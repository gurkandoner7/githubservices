package com.portal.githubservices.domain.repository

import com.portal.githubservices.data.base.NetworkResult
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem

interface GithubRepository {
    suspend fun getSearchUser(
        searchKeyword: String,
        page: Int,
        perPage: Int
    ): NetworkResult<GithubUserItem>

    suspend fun getUserDetails(user: String): NetworkResult<GithubUserDetailItem>
}