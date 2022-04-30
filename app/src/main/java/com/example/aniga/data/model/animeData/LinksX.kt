package com.example.aniga.data.model.animeData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksX(
    val related: String,
    val self: String
): Parcelable