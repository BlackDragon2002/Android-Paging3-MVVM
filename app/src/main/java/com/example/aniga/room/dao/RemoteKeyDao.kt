package com.example.aniga.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aniga.room.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(list: RemoteKey)

    @Query("SELECT * FROM RemoteKey WHERE id LIKE :id")
    suspend fun getRemoteKey(id: String):RemoteKey?

    @Query("DELETE FROM RemoteKey")
    suspend fun deleteAllRemoteKeys()
}