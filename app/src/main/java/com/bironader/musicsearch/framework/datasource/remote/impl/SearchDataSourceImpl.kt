package com.bironader.musicsearch.framework.datasource.remote.impl

import android.util.Log
import com.bironader.musicsearch.BuildConfig.BASE_URL
import com.bironader.musicsearch.Constant.QUERY
import com.bironader.musicsearch.Constant.SEARCH
import com.bironader.musicsearch.HeadersKey.AUTHORIZATION
import com.bironader.musicsearch.HeadersValue.BEARER
import com.bironader.musicsearch.framework.datasource.remote.Utils.getGson
import com.bironader.musicsearch.framework.datasource.remote.Utils.getStringResponse
import com.bironader.musicsearch.framework.datasource.remote.Utils.setDefaultHttpHeaders
import com.bironader.musicsearch.framework.datasource.remote.abstraction.Authenticator
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import com.bironader.musicsearch.framework.utils.toDomainModel
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.HttpRetryException
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.URL
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val prefDataSource: PrefDataSource,
    private val authenticator: Authenticator
) : SearchDataSource {

    override suspend fun searchForMusic(query: String) =


        flow {
            val queryParam = "?$QUERY=$query"
            val getMusicUrl = "$BASE_URL$SEARCH$queryParam"
            val url = URL(getMusicUrl)
            val httpConnection = url.openConnection() as HttpURLConnection
            try {
                val token = prefDataSource.getToken()
                httpConnection.requestMethod = "GET"
                httpConnection.setRequestProperty(
                    AUTHORIZATION,
                    "$BEARER $token"
                )

                setDefaultHttpHeaders(httpConnection)
                httpConnection.connect()
                Log.d("SearchDataSourceImpl", httpConnection.responseMessage)
                when (httpConnection.responseCode) {
                    HTTP_OK -> {
                        val responseList = paresMusicList(httpConnection)
                        emit(responseList.filter { it.title != null }.map { it.toDomainModel() })
                    }
                    HTTP_UNAUTHORIZED -> {
                        authenticator.authenticate()
                        throw HttpRetryException(httpConnection.responseMessage, HTTP_UNAUTHORIZED)
                    }

                }

            } catch (throwable: Throwable) {
                throw throwable
            } finally {
                httpConnection.disconnect()
            }


        }.flowOn(IO)

    private fun paresMusicList(connection: HttpURLConnection): List<Music> {
        val listType = object : TypeToken<List<Music>>() {}.type
        return getGson().fromJson(connection.getStringResponse(), listType) as List<Music>
    }


}