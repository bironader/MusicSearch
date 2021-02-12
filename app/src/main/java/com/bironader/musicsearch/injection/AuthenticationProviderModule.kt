package com.bironader.musicsearch.injection

import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.busniness.repositories.impl.AuthenticationRepositoryImpl
import com.bironader.musicsearch.busniness.usecases.abstraction.AuthenticationUseCase
import com.bironader.musicsearch.busniness.usecases.impl.AuthenticationUseCaseImpl
import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.AuthDataSource
import com.bironader.musicsearch.framework.datasource.remote.impl.AuthDataSourceImpl
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
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)


    @Singleton
    @Provides
    fun provideAuthDataSource(authService: AuthService): AuthDataSource =
        AuthDataSourceImpl(authService)

    @Singleton
    @Provides
    fun provideAuthRepo(dataSource: AuthDataSource): AuthenticationRepository =
        AuthenticationRepositoryImpl(dataSource)


    @Singleton
    @Provides
    fun provideAuthUseCase(authenticationRepository: AuthenticationRepository): AuthenticationUseCase =
        AuthenticationUseCaseImpl(authenticationRepository)

}