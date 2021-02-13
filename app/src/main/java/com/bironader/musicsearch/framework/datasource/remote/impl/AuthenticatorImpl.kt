package com.bironader.musicsearch.framework.datasource.remote.impl

import android.util.Log
import com.bironader.musicsearch.BuildConfig.*
import com.bironader.musicsearch.Constant.TOKEN
import com.bironader.musicsearch.HeadersKey
import com.bironader.musicsearch.framework.datasource.remote.Utils
import com.bironader.musicsearch.framework.datasource.remote.Utils.getGson
import com.bironader.musicsearch.framework.datasource.remote.Utils.getStringResponse
import com.bironader.musicsearch.framework.datasource.remote.Utils.setDefaultHttpHeaders
import com.bironader.musicsearch.framework.datasource.remote.abstraction.Authenticator
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import javax.inject.Inject
import kotlin.math.log


const val TAG = "AuthenticatorImpl"

class AuthenticatorImpl @Inject constructor(private val prefDataSource: PrefDataSource) :
    Authenticator {
    override fun authenticate() {
        val getTokenUrl = "$BASE_URL$TOKEN"
        val url = URL(getTokenUrl)
        val httpConnection = url.openConnection() as HttpURLConnection
        httpConnection.requestMethod = "POST"
        httpConnection.setRequestProperty(HeadersKey.GATEWAY_KEY, GATWAY_KEY)
        setDefaultHttpHeaders(httpConnection)
        httpConnection.connect()
        Log.d(TAG, httpConnection.responseMessage)
        Log.d(TAG, httpConnection.responseCode.toString())
        if (httpConnection.responseCode == HTTP_OK) {
            parseToken(httpConnection)?.accessToken?.let {
                prefDataSource.saveToken(it)
            }
        }
        httpConnection.disconnect()
    }

    private fun parseToken(connection: HttpURLConnection) =
        getGson().fromJson(connection.getStringResponse(), Authentication::class.java)
}
