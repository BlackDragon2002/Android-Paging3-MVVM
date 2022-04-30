package com.example.aniga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aniga.databinding.NetworkStateLayoutBinding

class AnimeLoadStateAdapter(
    private val retry:()->Unit
) :LoadStateAdapter<AnimeLoadStateAdapter.LoadStateViewHolder>(){

    inner class LoadStateViewHolder(val binding: NetworkStateLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            if (loadState is LoadState.Loading) {
                progressBar.isVisible=true
                errorMessage.isVisible=false
                btnRetry.isVisible=false
            }else{
                progressBar.isVisible=false
            }
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)=
        LoadStateViewHolder(
            NetworkStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
}