package com.portal.githubservices.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table ORDER BY `id` DESC")
    fun getFavorites(): List<FavoriteEntity>

    @Delete(entity = FavoriteEntity::class)
    fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favoriteEntity: FavoriteEntity)



}