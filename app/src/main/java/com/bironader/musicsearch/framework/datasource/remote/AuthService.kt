package com.bironader.musicsearch.framework.datasource.remote

import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.BuildConfig.GATWAY_KEY

import com.bironader.musicsearch.Constant.TOKEN
import com.bironader.musicsearch.HeadersKey.GATEWAY_KEY
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST(TOKEN)
     fun authenticate(): Call<Authentication>
}