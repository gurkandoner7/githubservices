package com.portal.githubservices.domain.usecase

import com.portal.githubservices.data.model.GithubUserDetailItem
import com.portal.githubservices.data.model.GithubUserItem
import com.portal.githubservices.domain.repository.GithubRepository
import javax.inject.Inject

class GithubUseCaseImpl @Inject constructor(private val githubRepository: GithubRepository) :
    GithubUseCase {
    override suspend fun getSearchUser(
        searchKeyword: String, page: Int, perPage: Int
    ): GithubUserItem = githubRepository.getSearchUser(searchKeyword, page, perPage)

    override suspend fun getUserRepositories(
        user: String,
    ): GithubUserDetailItem = githubRepository.getUserRepositories(user)

}