package com.example.aniga.data.model.animeData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PosterImage(
    val large: String,
    val medium: String,
    val original: String,
    val small: String,
    val tiny: String
): Parcelable