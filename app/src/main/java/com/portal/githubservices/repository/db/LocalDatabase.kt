package com.portal.githubservices.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserDetailEntity::class], version = 1, exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDetailDao(): UserDetailDao
}