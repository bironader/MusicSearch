package com.bironader.musicsearch.injection

import android.app.Application
import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.HeadersKey
import com.bironader.musicsearch.HeadersValue
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.injection.Annotations.RefreshToken
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthNetworkModule {


    private val baseUrl: String
        get() = BuildConfig.BASE_URL

    @RefreshToken
    @Provides
    @Singleton
    fun provideRetrofit(@RefreshToken okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @RefreshToken
    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckInterceptor: ChuckInterceptor,
        @RefreshToken headerInterceptor: Interceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(chuckInterceptor)
        }
        httpClientBuilder.addInterceptor(headerInterceptor)
        return httpClientBuilder.build()
    }

    @RefreshToken
    @Provides
    @Singleton
    fun provideHeaderInterceptor() = Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(HeadersKey.GATEWAY_KEY, BuildConfig.GATWAY_KEY)
            .addHeader(HeadersKey.ACCEPT, HeadersValue.APP_JSON)
            .addHeader(HeadersKey.CONTENT_TYPE, HeadersValue.APPLICATION_X_WWWW_FORM)
            .build()
        Timber.d(request.headers.toString())
        chain.proceed(request)
    }

}