package com.portal.githubservices.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchResultDao {
    @Query("SELECT * FROM search_result_table ORDER BY id DESC LIMIT 30")
    fun getAllSearchResults(): List<SearchResultEntity>

    @Query("DELETE FROM search_result_table")
    fun deleteAllSearchResults()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchResult(searchResultEntity: SearchResultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchResultList(searchResultEntities: List<SearchResultEntity>)

    @Query("UPDATE search_result_table SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: Long, isFavorite: Boolean)
}