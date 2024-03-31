package com.portal.githubservices.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName ="search_result_table")
data class SearchResultEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name ="login")val login: String?,
    @ColumnInfo(name ="avatar_url")val avatar_url: String?,
)