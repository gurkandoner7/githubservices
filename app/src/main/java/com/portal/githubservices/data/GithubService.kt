package com.portal.githubservices.data

import com.portal.githubservices.domain.model.response.GithubUserDetailResponse
import com.portal.githubservices.domain.model.response.GithubUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/search/users?")
    suspend fun getSearchUser(
        @Query(value = "q", encoded = true) searchKeyword: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<GithubUsersResponse>

    @GET("/users/{user}")
    suspend fun getUserDetails(
        @Path("user") user: String,
    ): Response<GithubUserDetailResponse>
}