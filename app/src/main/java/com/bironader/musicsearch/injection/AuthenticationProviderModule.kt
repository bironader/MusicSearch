package com.bironader.musicsearch.injection

import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.injection.Annotations.RefreshToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationProviderModule {
    @Singleton
    @Provides
    fun provideAuthService(@RefreshToken retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)


}