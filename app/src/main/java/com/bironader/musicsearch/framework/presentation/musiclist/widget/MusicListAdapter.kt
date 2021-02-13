package com.bironader.musicsearch.framework.presentation.musiclist.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bironader.musicsearch.R
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.databinding.MusicItemBinding
import com.bironader.musicsearch.framework.presentation.musiclist.widget.MusicListAdapter.MusicHolder
import com.example.nyarticles.framework.utils.ItemClickListener
import javax.inject.Inject

class MusicListAdapter @Inject constructor() :
    ListAdapter<MusicDomainModel, MusicHolder>(MusicDiffCallBack()) {

    lateinit var itemClickListener: ItemClickListener<MusicDomainModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        val binding = DataBindingUtil.inflate<MusicItemBinding>(
            LayoutInflater.from(parent.context), R.layout.music_item, parent, false
        )
        return MusicHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class MusicHolder(private val binding: MusicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MusicDomainModel) {
            binding.item = item
            binding.root.setOnClickListener {
                itemClickListener.itemClick(item)
            }

        }

    }

}

class MusicDiffCallBack : DiffUtil.ItemCallback<MusicDomainModel>() {

    override fun areItemsTheSame(oldItem: MusicDomainModel, newItem: MusicDomainModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MusicDomainModel, newItem: MusicDomainModel) =
        oldItem == newItem
}
