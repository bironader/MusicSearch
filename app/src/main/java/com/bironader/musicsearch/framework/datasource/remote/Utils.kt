package com.bironader.musicsearch.framework.datasource.remote

import android.util.Log
import com.bironader.musicsearch.HeadersKey.ACCEPT
import com.bironader.musicsearch.HeadersKey.CONTENT_TYPE
import com.bironader.musicsearch.HeadersValue.APPLICATION_X_WWWW_FORM
import com.bironader.musicsearch.HeadersValue.APP_JSON
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.GsonBuilder
import java.net.HttpRetryException
import java.net.HttpURLConnection

object Utils {
    fun setDefaultHttpHeaders(httpURLConnection: HttpURLConnection) {
        httpURLConnection.setRequestProperty(ACCEPT, APP_JSON)
        httpURLConnection.setRequestProperty(
            CONTENT_TYPE,
            APPLICATION_X_WWWW_FORM
        )
    }

    fun getGson() = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun HttpURLConnection.getStringResponse(): String {
        val response = StringBuffer()
        try {
            this.inputStream.bufferedReader().use { reader ->
                var `in` = ""
                while (reader.readLine().also { `in` = it } != null)
                    response.append(`in`)
            }
        } catch (throwable: Throwable) {
            Log.d("AuthenticatorImpl", throwable.message.toString())
        }

        return response.toString()
    }

}

