package com.bironader.musicsearch.framework.datasource.remote

import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.BuildConfig.GATWAY_KEY
import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.Constant.GATEWAY_KEY
import com.bironader.musicsearch.Constant.TOKEN
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST(TOKEN)
     fun authenticate(@Header(GATEWAY_KEY) gateWayKey: String = GATWAY_KEY): Authentication
}