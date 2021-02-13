package com.bironader.musicsearch.framework.presentation.musicdetail

import android.os.Bundle
import com.bironader.musicsearch.R
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.databinding.FragmentMusicDetailBinding
import com.bironader.musicsearch.framework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicDetailFragment : BaseFragment<FragmentMusicDetailBinding>() {

    private lateinit var musicItem: MusicDomainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        musicItem = MusicDetailFragmentArgs.fromBundle(requireArguments()).music
    }

    override fun bindViews() {
        binding.music = musicItem
    }

    override fun getLayoutResId() = R.layout.fragment_music_detail
}