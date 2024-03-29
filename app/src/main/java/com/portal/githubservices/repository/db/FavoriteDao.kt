package com.portal.githubservices.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table ORDER BY `id` DESC")
    fun getAllFavorites(): List<FavoriteEntity>

    @Query("DELETE FROM favorite_table")
    fun deleteAllFavorites()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favoriteEntity: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteList(favoriteEntities: List<FavoriteEntity>)

}