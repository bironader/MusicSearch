package com.bironader.musicsearch.framework.presentaion.musiclist.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicCustomListView(context: Context, attributes: AttributeSet) :
    RecyclerView(context, attributes) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    fun clean() {
        this.adapter = null
    }
}