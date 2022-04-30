package com.example.aniga.data.model.animeData
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Data(
    val attributes: Attributes?=null,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val links: Links?=null,
    val relationships: Relationships?=null,
    val type: String?=null
): Parcelable