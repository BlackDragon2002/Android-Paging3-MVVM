package com.example.aniga.api

import com.example.aniga.data.model.animeData.AnimeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("anime")
    suspend fun getAnimeList(
        @Query("page[limit]")
        limit:Int,
        @Query("page[offset]")
        offset:Int,
        @Query("filter[text]")
        text: String?
    ): AnimeList
}