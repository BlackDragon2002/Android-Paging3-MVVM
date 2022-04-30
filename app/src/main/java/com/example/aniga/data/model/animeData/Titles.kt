package com.example.aniga.data.model.animeData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Titles(
    val en: String?=null,
    val en_jp: String?=null,
    val en_us: String?=null,
    val ja_jp: String?=null
): Parcelable