package com.example.aniga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aniga.R
import com.example.aniga.data.model.animeData.Data
import com.example.aniga.databinding.LayoutBinding
import com.example.aniga.util.OnItemClickListener

class AnimeListAdapter(
    private val listener:OnItemClickListener
) :PagingDataAdapter<Data, AnimeListAdapter.ViewHolder>(DiffCallBack){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=
            LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }


    inner class ViewHolder(private val binding:LayoutBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position=bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val data=getItem(position)
                        if (data != null) {
                            listener.onItemClicked(data)
                        }
                    }
                }
            }
        }

        fun bind(data:Data) {
            binding.apply {
                tvTitle.text =data.attributes?.titles?.en_jp
                ivPoster.load(data.attributes?.posterImage?.original){
                    crossfade(true)
                    error(R.drawable.ic_error)
                    placeholder(R.drawable.ic_loading)
                }
            }
        }

    }
    object DiffCallBack:DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }




}