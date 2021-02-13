//package com.bironader.musicsearch.injection
//
//import android.app.Application
//import com.bironader.musicsearch.BuildConfig
//import com.bironader.musicsearch.BuildConfig.GATWAY_KEY
//import com.bironader.musicsearch.Constant
//import com.bironader.musicsearch.HeadersKey
//import com.bironader.musicsearch.HeadersKey.ACCEPT
//import com.bironader.musicsearch.HeadersKey.AUTHORIZATION
//import com.bironader.musicsearch.HeadersKey.CONTENT_TYPE
//import com.bironader.musicsearch.HeadersKey.GATEWAY_KEY
//import com.bironader.musicsearch.HeadersValue.APPLICATION_X_WWWW_FORM
//import com.bironader.musicsearch.HeadersValue.APP_JSON
//import com.bironader.musicsearch.HeadersValue.BEARER
//import com.bironader.musicsearch.framework.datasource.remote.AuthService
//import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
//import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
//import com.bironader.musicsearch.injection.Annotations.Requests
//import com.facebook.stetho.okhttp3.StethoInterceptor
//import com.google.gson.FieldNamingPolicy
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.readystatesoftware.chuck.ChuckInterceptor
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.*
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import timber.log.Timber
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//
//const val NOT_AUTHORIZED = 401
//
//@Module
//@InstallIn(SingletonComponent::class)
//class NetworkModule {
//
//
//    private val baseUrl: String
//        get() = BuildConfig.BASE_URL
//
//    @Requests
//    @Provides
//    @Singleton
//    fun provideRetrofit(@Requests okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//
//
//    @Requests
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor,
//        stethoInterceptor: StethoInterceptor,
//        chuckInterceptor: ChuckInterceptor,
//        @Requests headerInterceptor: Interceptor,
//        authenticator: Authenticator
//    ): OkHttpClient {
//        val httpClientBuilder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
//            httpClientBuilder.addInterceptor(chuckInterceptor)
//            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
//            httpClientBuilder.addNetworkInterceptor(stethoInterceptor)
//        }
//        httpClientBuilder.authenticator(authenticator)
//        httpClientBuilder.addNetworkInterceptor(headerInterceptor)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .connectTimeout(20, TimeUnit.SECONDS)
//
//        return httpClientBuilder.build()
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson {
//        return GsonBuilder()
//            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .create()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        val logger = object : HttpLoggingInterceptor.Logger {
//            override fun log(message: String) {
//                Timber.d(message)
//            }
//        }
//        val loggingInterceptor = HttpLoggingInterceptor(logger)
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return loggingInterceptor
//    }
//
//    @Provides
//    @Singleton
//    fun provideStethoInterceptor() = StethoInterceptor()
//
//
//    @Provides
//    @Singleton
//    fun provideChuckInterceptor(application: Application) =
//        ChuckInterceptor(application.applicationContext)
//
//
//    @Requests
//    @Provides
//    @Singleton
//    fun provideHeaderInterceptor(dataStoreSource: PrefDataSource) = Interceptor { chain ->
//        val token = dataStoreSource.getToken()
//        var request = chain.request()
//        request = request.newBuilder()
//            .addHeader(AUTHORIZATION, "$BEARER $token")
//            .addHeader(ACCEPT, APP_JSON)
//            .addHeader(CONTENT_TYPE, APPLICATION_X_WWWW_FORM)
//            .build()
//        Timber.d(request.headers.toString())
//        chain.proceed(request)
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideAuthenticator(prefDataSource: PrefDataSource, authService: AuthService) =
//        object : Authenticator {
//            override fun authenticate(route: Route?, response: Response): Request {
//                val token = authService.authenticate().execute().body()!!.accessToken!!
//                prefDataSource.saveToken(token = token)
//                return response.request.newBuilder()
//                    .header(AUTHORIZATION, "$BEARER $token")
//                    .build()
//            }
//        }
//
//
//}