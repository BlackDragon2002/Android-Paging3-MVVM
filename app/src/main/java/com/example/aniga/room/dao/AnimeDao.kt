package com.example.aniga.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aniga.data.model.animeData.Data

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(anime: List<Data>)

    @Query("SELECT * FROM Data")
    fun pagingSource(): PagingSource<Int, Data>

    @Query("DELETE FROM Data")
    suspend fun deleteAllAnimeList()
}