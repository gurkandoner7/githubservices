package com.portal.githubservices.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    val id: Int? = 0,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: String
)
