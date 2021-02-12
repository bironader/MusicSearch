package com.bironader.musicsearch.framework.datasource.remote.abstraction

import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import retrofit2.http.Header

interface AuthDataSource {

    suspend fun authenticate(): Authentication
}