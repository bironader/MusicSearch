package com.bironader.musicsearch.framework.utils

import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.injection.NetworkModule_ProvideGsonFactory.provideGson
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Utils {

    fun getRetrofitManualInjection() =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(getGsonManualInjection()))
            .build()



   private fun getGsonManualInjection(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

}