package com.portal.githubservices.domain.usecase.room

import com.portal.githubservices.repository.db.UserDetailEntity
import com.portal.githubservices.repository.db.LocalRepository
import javax.inject.Inject

class LocalUseCaseImpl @Inject constructor(private val localRepository: LocalRepository) :
    LocalUseCase {
    override suspend fun getAllUserDetails(): List<UserDetailEntity> =
        localRepository.getAllUserDetails()

    override suspend fun addUserDetail(userDetailEntity: UserDetailEntity) =
        localRepository.addUserDetail(userDetailEntity)

    override suspend fun addUserDetailList(userDetailEntity: List<UserDetailEntity>) =
        localRepository.addUserDetailList(userDetailEntity)

    override suspend fun deleteAllUserDetails() = localRepository.deleteAllUserDetails()
}