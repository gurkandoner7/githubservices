package com.portal.githubservices.repository.db.userdetail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail_table")
data class UserDetailEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatar_url: String?,
    @ColumnInfo(name = "public_repos") val public_repos: String?,
    @ColumnInfo(name = "followers") val followers: String?,
    @ColumnInfo(name = "following") val following: String?,
)
