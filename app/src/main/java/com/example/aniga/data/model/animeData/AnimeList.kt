package com.example.aniga.data.model.animeData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimeList(
    @PrimaryKey(autoGenerate = false)
    val `data`: List<Data>
)