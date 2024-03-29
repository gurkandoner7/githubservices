package com.portal.githubservices.domain.repository

import com.portal.githubservices.data.GithubService
import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.mapper.toGithubUserItem
import com.portal.githubservices.domain.mapper.toGithubUserRepoItem
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
) : GithubRepository {

    override suspend fun getSearchUser(
        searchKeyword: String, page: Int, perPage: Int
    ): GithubUserItem {
        try {
            return githubService.getSearchUser(searchKeyword, page, perPage).toGithubUserItem()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUserRepositories(
        user: String
    ): GithubUserDetailItem {
        try {
            return githubService.getUserRepositories(user).toGithubUserRepoItem()
        } catch (e: Exception) {
            throw e
        }

    }
}