package com.example.aniga.ui.animeList

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aniga.R
import com.example.aniga.adapter.AnimeListAdapter
import com.example.aniga.adapter.AnimeLoadStateAdapter
import com.example.aniga.api.ApiService
import com.example.aniga.data.model.animeData.Data
import com.example.aniga.databinding.AnimeListFragmentBinding
import com.example.aniga.util.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


@AndroidEntryPoint
class AnimeListFragment:Fragment(R.layout.anime_list_fragment),OnItemClickListener {
    private lateinit var binding: AnimeListFragmentBinding

    @Inject
    lateinit var apiService: ApiService

    private val viewModel: AnimeListViewModel by viewModels()
    private lateinit var animeAdapter: AnimeListAdapter


    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        binding= AnimeListFragmentBinding.bind(view)
        setUpRecyclerView()
        collectList(null)

    }

    private fun setUpRecyclerView() {
        animeAdapter= AnimeListAdapter(this)
        binding.apply {
            recyclerView.apply {
                adapter=animeAdapter
                layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                this.adapter=animeAdapter.withLoadStateHeaderAndFooter(
                    header = AnimeLoadStateAdapter{ animeAdapter.retry() },
                    footer = AnimeLoadStateAdapter{ animeAdapter.retry() }
                )
            }

            animeAdapter.addLoadStateListener {loadstate->
                progressBar.isVisible=loadstate.source.refresh is LoadState.Loading
                recyclerView.isVisible=loadstate.source.refresh is LoadState.NotLoading
                btnRetry.isVisible=loadstate.source.refresh is LoadState.Error
                errorMessage.isVisible=loadstate.source.refresh is LoadState.Error
                btnRetry.setOnClickListener {
                    animeAdapter.retry()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search,menu)
        val searchItem=menu.findItem(R.id.action_search)
        searchView=searchItem?.actionView as SearchView

        val pendingQuery=viewModel.searchQuery.value

        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery,false)
        }

        searchView.setOnQueryTextListener(
            object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?):Boolean{
                    viewModel.searchQuery.value=query
                    setUpRecyclerView()
                    collectList(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            }
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun collectList(query:String?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getAnimeList(query).collectLatest {
                animeAdapter.submitData(it)
            }
        }
    }

    private suspend fun callAdapter() {
    }
    override fun onItemClicked(data: Data) {
        val action=
            AnimeListFragmentDirections.actionAnimeListFragmentToAnimeDetailFragment(data)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchView.setOnQueryTextListener(null)
    }

}