package com.example.aniga.pagination

import android.util.Log
import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.aniga.api.ApiService
import com.example.aniga.data.model.animeData.Data
import com.example.aniga.room.AnimeDatabase
import com.example.aniga.room.RemoteKey
import com.example.aniga.room.dao.AnimeDao
import com.example.aniga.room.dao.RemoteKeyDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(ActivityRetainedComponent::class)
class AnimeRemoteMediator
    @Inject constructor(
    private val apiService: ApiService,
    private val db:AnimeDatabase,
    private val query:String?
):RemoteMediator<Int,Data>(){
    private val remoteKeyDao:RemoteKeyDao=db.remoteKeyDao()
    private val animeDao: AnimeDao=db.animeDao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Data>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH-> {
                    Log.d("debug","refresh")
                    null
                }
                LoadType.PREPEND->{
                    Log.d("debug","prepend")
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND->{
                    Log.d("debug","append")
                    val listItem= state.lastItemOrNull()
                        ?: return MediatorResult.Success(true)

                    val remoteKey=db.withTransaction {
                        remoteKeyDao.getRemoteKey(listItem.id)
                    }
                    remoteKey?.next?:1
                }
            }


            val count=page?:1
            val response=apiService.getAnimeList(
                20,
                count,
                null
            )

            val list=response.data.map {
                RemoteKey(
                    it.id,
                    it.id.toInt()
                )
            }

            if(query==null){
                db.withTransaction {
                    if(loadType==LoadType.REFRESH){
                        animeDao.deleteAllAnimeList()
                        remoteKeyDao.deleteAllRemoteKeys()
                    }

                    remoteKeyDao.insertRemoteKey(list.last())
                    animeDao.insertAnimeList(response.data)

                }
            }

            MediatorResult.Success(endOfPaginationReached = response.data.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}