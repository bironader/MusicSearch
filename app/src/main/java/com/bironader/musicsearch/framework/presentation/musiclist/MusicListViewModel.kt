package com.bironader.musicsearch.framework.presentation.musiclist

import androidx.lifecycle.*
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.framework.utils.EspressoIdlingResource
import com.bironader.musicsearch.framework.utils.Resource
import com.bironader.musicsearch.framework.utils.Resource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.HttpRetryException
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MusicListViewModel @Inject constructor(private val getMusicUseCase: GetMusicUseCase) :
    ViewModel() {

    val stateLiveData: LiveData<Resource<List<MusicDomainModel>>>
        get() = _stateLiveData
    private val _stateLiveData = MutableLiveData<Resource<List<MusicDomainModel>>>()

    private val _queryLiveData = MutableLiveData<String>()


    fun setQuery(query: String) {
        _queryLiveData.value = query
    }

    init {
        search()
    }

    private fun search() {
        viewModelScope.launch {
            _queryLiveData.asFlow()
                .debounce(200) // wait  before  search
                .filter {
                    return@filter it.isNotEmpty() && it.length > 2
                } // don't preform search in case query is empty or less than 2 char
                .distinctUntilChanged() // avoid duplicated requests
                .flatMapLatest {
                    getMusicUseCase.getMusics(it)
                        .onStart {
                            _stateLiveData.value = Loading()
                        }

                        .retryWhen { cause, attempt ->
                            return@retryWhen cause is HttpRetryException && attempt < 3
                        }
                        .catch { throwable ->
                            _stateLiveData.value = Failure(throwable)
                            emitAll(emptyFlow())
                        }
                }
                // we only care for the last emission
                .collect {
                    _stateLiveData.value = Success(it)
                }
        }
    }

}