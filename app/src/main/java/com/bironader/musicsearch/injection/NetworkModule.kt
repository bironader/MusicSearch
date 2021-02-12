package com.bironader.musicsearch.injection

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.bironader.musicsearch.BuildConfig
import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.Constant.AUTHORIZATION
import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.AuthDataSource
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.utils.Utils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val NOT_AUTHORIZED = 401

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    private val baseUrl: String
        get() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor,
        chuckInterceptor: ChuckInterceptor,
        prefDataSource: PrefDataSource
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(chuckInterceptor)
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            httpClientBuilder.addNetworkInterceptor(stethoInterceptor)
        }
        httpClientBuilder.addInterceptor(provideAuthInterceptor())
        httpClientBuilder.addNetworkInterceptor(provideHeaderInterceptor(prefDataSource))
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideStethoInterceptor() = StethoInterceptor()


    @Provides
    @Singleton
    fun provideChuckInterceptor(application: Application) =
        ChuckInterceptor(application.applicationContext)


    @Provides
    @Singleton
    fun provideHeaderInterceptor(dataStoreSource: PrefDataSource) = Interceptor { chain ->
        val token = dataStoreSource.getToken()
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(AUTHORIZATION, "${Constant.BEARER} $token")
            .build()
        Timber.d(request.headers.toString())
        chain.proceed(request)
    }


    @Provides
    @Singleton
    fun provideAuthInterceptor() =
        object : Authenticator {
            override fun authenticate(route: Route?, response: Response): Request? {
                val service = Utils.getRetrofitManualInjection().create(AuthService::class.java)
                val token = service.authenticate()
                return response.request.newBuilder()
                    .header(AUTHORIZATION, "${Constant.BEARER} ${token.accessToken}")
                    .build()
            }
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val response = chain.proceed(chain.request())
//                if (response.code == NOT_AUTHORIZED) {

//                    MainScope().launch(IO) {
//                        service.authenticate()
//                    }
//                }
//
//                return response
//            }

        }


}