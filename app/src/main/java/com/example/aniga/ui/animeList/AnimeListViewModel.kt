package com.example.aniga.ui.animeList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.aniga.pagination.Repository
import com.example.aniga.data.model.animeData.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel
@OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val repository: Repository,
    private val state:SavedStateHandle

): ViewModel(){

    val searchQuery= state.getLiveData("Search Query","")

    @OptIn(ExperimentalPagingApi::class)
    fun getAnimeList(query:String?): Flow<PagingData<Data>> {
        Log.d("Checks","$query")
        return if(query==null){
            repository.fetchAnimeListFromRemote(query).cachedIn(viewModelScope)
        } else{
            repository.fetchAnimeListFromPaging(query).cachedIn(viewModelScope)
        }
    }

}