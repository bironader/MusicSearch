package com.bironader.musicsearch.framework.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bironader.musicsearch.busniness.usecases.abstraction.AuthenticationUseCase
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import com.bironader.musicsearch.framework.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase) :
    ViewModel() {


    val stateLiveData: LiveData<Resource<Authentication>>
        get() = _stateLiveData
    private val _stateLiveData = MutableLiveData<Resource<Authentication>>()


    init {
        Timber.d("asd")
        viewModelScope.launch {
            authenticationUseCase.authenticate()
                .catch {
                    Timber.d(it.message)
                }
                .collect {
                    Timber.d(it.accessToken)
                }

        }
    }
}