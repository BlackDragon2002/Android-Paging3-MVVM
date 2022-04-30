package com.example.aniga.data.model.animeData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attributes(
    val abbreviatedTitles: List<String>,
    val averageRating: String?=null,
    val description: String?=null,
    val popularityRank: Int?=null,
    val posterImage: PosterImage?=null,
    val ratingRank: Int?=null,
    val status: String?=null,
    val titles: Titles?=null,
    val youtubeVideoId: String?=null
): Parcelable