package com.example.aniga.data.model.animeData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Relationships(
    val castings: Castings,
    val genres: Genres
): Parcelable