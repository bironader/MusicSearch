package com.bironader.musicsearch.framework.presentation.musiclist.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.example.nyarticles.framework.utils.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MusicCustomListView(context: Context, attributes: AttributeSet) :
    RecyclerView(context, attributes) {

    @Inject
    lateinit var musicListAdapter: MusicListAdapter

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.adapter = musicListAdapter
    }

    fun showItems(list: List<MusicDomainModel>) = musicListAdapter.submitList(list)

    fun setOnItemClick(itemClickListener: ItemClickListener<MusicDomainModel>) {
        musicListAdapter.itemClickListener = itemClickListener
    }
    fun clean() {
        this.adapter = null
    }
}