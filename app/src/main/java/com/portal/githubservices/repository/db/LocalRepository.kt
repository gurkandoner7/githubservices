package com.portal.githubservices.repository.db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject internal constructor(
    localDatabase: LocalDatabase
) {
    private val userDetailDao = localDatabase.userDetailDao()

    fun getAllUserDetails(): List<UserDetailEntity> = userDetailDao.getAllUserDetails()

    fun addUserDetail(userDetailEntity: UserDetailEntity) =
        userDetailDao.addUserDetail(userDetailEntity)

    fun deleteAllUserDetails() = userDetailDao.deleteAllUserDetails()

    fun addUserDetailList(userDetailEntity: List<UserDetailEntity>) =
        userDetailDao.addUserDetailList(userDetailEntity)
}