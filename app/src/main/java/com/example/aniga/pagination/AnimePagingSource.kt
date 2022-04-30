package com.example.aniga.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aniga.api.ApiService
import com.example.aniga.data.model.animeData.Data
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX=1

class AnimePagingSource
@Inject constructor(
    private val apiService: ApiService,
    private val query: String?
): PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position=params.key?:1
        return try {

            val response=apiService.getAnimeList(params.loadSize,position,query)
            val animeList=response.data

            LoadResult.Page(
                data = animeList,
                prevKey = if(position==1) null else position-1,
                nextKey = if(animeList.isEmpty()) null else position+1
            )
        } catch (e: IOException) {
            Log.e("list", e.localizedMessage?:"IOException")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e("list", e.localizedMessage?:"HTTPException")
            LoadResult.Error(e)
        }
    }
}