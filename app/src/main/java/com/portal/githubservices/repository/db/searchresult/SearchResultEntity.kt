package com.portal.githubservices.repository.db.searchresult

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_result_table")
data class SearchResultEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "avatar_url") val avatar_url: String?,

)