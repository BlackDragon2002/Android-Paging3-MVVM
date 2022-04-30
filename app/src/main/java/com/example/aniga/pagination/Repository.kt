package com.example.aniga.pagination

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.aniga.api.ApiService
import com.example.aniga.data.model.animeData.Data
import com.example.aniga.room.AnimeDatabase
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val db:AnimeDatabase
) {

     fun fetchAnimeListFromRemote(query: String?): Flow<PagingData<Data>> =
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 15,
                    enablePlaceholders = false,
                    initialLoadSize = 10,
                    maxSize = 500,
                ),
                remoteMediator = AnimeRemoteMediator(apiService,db,query)
            )
            {
                db.animeDao().pagingSource()
            }.flow

    fun fetchAnimeListFromPaging(query: String?): Flow<PagingData<Data>> =
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 15,
                    enablePlaceholders = false,
                    initialLoadSize = 10,
                    maxSize = 500,
                ),
                pagingSourceFactory ={
                    AnimePagingSource(apiService,query)
                },
            ).flow

}