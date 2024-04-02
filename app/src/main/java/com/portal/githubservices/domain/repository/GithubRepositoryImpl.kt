package com.portal.githubservices.domain.repository

import com.portal.githubservices.data.GithubService
import com.portal.githubservices.data.base.NetworkResult
import com.portal.githubservices.data.base.safeApiCall
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.mapper.toGithubUserDetailItem
import com.portal.githubservices.domain.mapper.toGithubUserItem
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
) : GithubRepository {

    override suspend fun getSearchUser(
        searchKeyword: String, page: Int, perPage: Int
    ): NetworkResult<GithubUserItem> {
        return safeApiCall {
            githubService.getSearchUser(searchKeyword, page, perPage)
        }.map {
            it.toGithubUserItem()
        }
    }

    override suspend fun getUserDetails(
        user: String
    ): NetworkResult<GithubUserDetailItem> {
        return safeApiCall {
            githubService.getUserDetails(user)
        }.map {
            it.toGithubUserDetailItem()
        }
    }
}