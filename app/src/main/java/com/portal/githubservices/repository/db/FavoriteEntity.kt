package com.portal.githubservices.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name ="login")val login: String?,
    @ColumnInfo(name ="avatar_url")val avatar_url: String?,
    @ColumnInfo(name ="isFavorite")val isFavorite: Boolean
)