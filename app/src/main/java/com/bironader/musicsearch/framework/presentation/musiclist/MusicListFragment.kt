package com.bironader.musicsearch.framework.presentation.musiclist

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import com.bironader.musicsearch.R
import com.bironader.musicsearch.databinding.FragmentMusicListBinding
import com.bironader.musicsearch.framework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicListFragment : BaseFragment<FragmentMusicListBinding>(), SearchView.OnQueryTextListener {
    override fun bindViews() {
        setHasOptionsMenu(true)
    }

    override fun getLayoutResId() = R.layout.fragment_music_list

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as (SearchView)
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater);

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}