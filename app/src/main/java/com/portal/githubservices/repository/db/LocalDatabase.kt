package com.portal.githubservices.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.portal.githubservices.repository.db.favorite.FavoriteDao
import com.portal.githubservices.repository.db.favorite.FavoriteEntity
import com.portal.githubservices.repository.db.searchresult.SearchResultDao
import com.portal.githubservices.repository.db.searchresult.SearchResultEntity
import com.portal.githubservices.repository.db.userdetail.UserDetailDao
import com.portal.githubservices.repository.db.userdetail.UserDetailEntity

@Database(
    entities = [UserDetailEntity::class, SearchResultEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDetailDao(): UserDetailDao
    abstract fun searchResultDao(): SearchResultDao
    abstract fun favoriteDao(): FavoriteDao
}