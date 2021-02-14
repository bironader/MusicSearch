package com.bironader.musicsearch.framework.presentation.musiclist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bironader.musicsearch.R
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.databinding.FragmentMusicListBinding
import com.bironader.musicsearch.framework.presentation.base.BaseFragment
import com.bironader.musicsearch.framework.utils.EspressoIdlingResource
import com.bironader.musicsearch.framework.utils.Resource
import com.bironader.musicsearch.framework.utils.Resource.*
import com.bironader.musicsearch.framework.utils.getMessage
import com.bironader.musicsearch.framework.utils.getType
import com.example.nyarticles.framework.utils.ItemClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MusicListFragment : BaseFragment<FragmentMusicListBinding>(), SearchView.OnQueryTextListener,
    ItemClickListener<MusicDomainModel> {


    private val viewModel: MusicListViewModel by viewModels()

    override fun bindViews() {
        binding.musicList.setOnItemClick(this)
        setHasOptionsMenu(true)

    }

    override fun observe() {
        super.observe()
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Success -> {
                    binding.musicList.showItems(it.data)
                    binding.isEmpty = it.data.isEmpty()
                    binding.progressCircular.visibility = GONE
                    EspressoIdlingResource.decrement()


                }
                is Failure -> {
                    handleErrors(it.throwable.getType())
                    binding.progressCircular.visibility = GONE
                    EspressoIdlingResource.decrement()

                }
                is Loading -> {
                    binding.progressCircular.visibility = VISIBLE
                }

            }

        })


    }


    override fun getLayoutResId() = R.layout.fragment_music_list

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem?.actionView as (SearchView)
        searchView.setOnQueryTextListener(this)
        searchView.requestFocus()
        searchView.queryHint = getString(R.string.search_for_music)
        menuItem.expandActionView()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?)  = true

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText?.isNotEmpty()!!)  EspressoIdlingResource.increment()
        viewModel.setQuery(if (newText.isNullOrEmpty()) "" else newText)

        return true
    }


    override fun onDestroyView() {
        binding.musicList.clean()
        super.onDestroyView()
    }

    override fun itemClick(item: MusicDomainModel) {
        val action = MusicListFragmentDirections.actionMusicListFragmentToMusicDetailFragment(
            item,
            item.title
        )
        findNavController().navigate(action)
    }
}