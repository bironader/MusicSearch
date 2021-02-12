package com.bironader.musicsearch.framework.presentation.musiclist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.bironader.musicsearch.R
import com.bironader.musicsearch.databinding.FragmentMusicListBinding
import com.bironader.musicsearch.framework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MusicListFragment : BaseFragment<FragmentMusicListBinding>(), SearchView.OnQueryTextListener {


    private val viewModel: MusicListViewModel by viewModels()

    override fun bindViews() {
        setHasOptionsMenu(true)

    }


    override fun getLayoutResId() = R.layout.fragment_music_list

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as (SearchView)
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.setQuery(if (newText.isNullOrEmpty()) "" else newText)
        return true
    }
}