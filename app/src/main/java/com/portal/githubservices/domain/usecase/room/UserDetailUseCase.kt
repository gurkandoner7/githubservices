package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.userdetail.UserDetailEntity

interface UserDetailUseCase {
    suspend fun getAllUserDetails(): List<UserDetailEntity>
    suspend fun addUserDetail(userDetailEntity: UserDetailEntity)
    suspend fun addUserDetailList(userDetailEntity: List<UserDetailEntity>)
    suspend fun deleteAllUserDetails()
}