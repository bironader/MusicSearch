package com.bironader.musicsearch.framework.presentation.musiclist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import com.bironader.musicsearch.framework.utils.Resource
import com.bironader.musicsearch.framework.utils.Resource.Failure
import com.bironader.musicsearch.framework.utils.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MusicListViewModel @Inject constructor(private val getMusicUseCase: GetMusicUseCase) :
    ViewModel() {

    val stateLiveData: LiveData<Resource<List<MusicDomainModel>>>
        get() = _stateLiveData
    private val _stateLiveData = MutableLiveData<Resource<List<MusicDomainModel>>>()


    private val _queryState = MutableStateFlow("")


    fun setQuery(query: String) {
        _queryState.value = query
    }

    init {
        search()
    }

    private fun search() {
        viewModelScope.launch {
            _queryState.debounce(200) // wait  before  search
                .filter { return@filter it.isNotEmpty() && it.length > 2 } // don't preform search in case query is empty or less than 2 char
                .distinctUntilChanged() // avoid duplicated requests
                .flatMapLatest { getMusicUseCase.getMusics(it) } // we only care for the last emission
                .catch { _stateLiveData.value = Failure(it) }
                .collect {
                    _stateLiveData.value = Success(it)
                }
        }

    }


}