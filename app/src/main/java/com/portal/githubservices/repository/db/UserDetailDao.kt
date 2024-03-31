package com.portal.githubservices.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDetailDao {

    @Query("SELECT * FROM user_detail_table ORDER BY `id` DESC")
    fun getAllUserDetails(): List<UserDetailEntity>

    @Query("DELETE FROM user_detail_table")
    fun deleteAllUserDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserDetail(userDetailEntity: UserDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserDetailList(userDetailEntities: List<UserDetailEntity>)



}