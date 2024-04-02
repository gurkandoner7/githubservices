package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.LocalRepository
import com.portal.githubservices.repository.db.userdetail.UserDetailEntity
import javax.inject.Inject

class UserDetailUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : UserDetailUseCase {
    override suspend fun getAllUserDetails(): List<UserDetailEntity> =
        localRepository.getAllUserDetails()

    override suspend fun addUserDetail(userDetailEntity: UserDetailEntity) =
        localRepository.addUserDetail(userDetailEntity)

    override suspend fun addUserDetailList(userDetailEntity: List<UserDetailEntity>) =
        localRepository.addUserDetailList(userDetailEntity)

    override suspend fun deleteAllUserDetails() = localRepository.deleteAllUserDetails()
}