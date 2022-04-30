package com.example.aniga.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aniga.data.model.animeData.Data
import com.example.aniga.room.dao.AnimeDao
import com.example.aniga.room.dao.RemoteKeyDao

@Database(
entities = [Data::class,RemoteKey::class],
version =2
)
@TypeConverters(AnimeDataTypeConvertor::class)

abstract class AnimeDatabase:RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}