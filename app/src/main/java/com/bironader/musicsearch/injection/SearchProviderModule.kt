package com.bironader.musicsearch.injection

import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.busniness.repositories.impl.GetMusicRepoImpl
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.busniness.usecases.impl.GetMusicUseCaseImpl
import com.bironader.musicsearch.framework.datasource.remote.abstraction.Authenticator
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.impl.AuthenticatorImpl
import com.bironader.musicsearch.framework.datasource.remote.impl.SearchDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchProviderModule {

    @Singleton
    @Provides
    fun provideAuthenticator(prefDataSource: PrefDataSource): Authenticator =
        AuthenticatorImpl(prefDataSource)


    @Singleton
    @Provides
    fun providerSearchDataSource(
        prefDataSource: PrefDataSource,
        authenticator: Authenticator
    ): SearchDataSource =
        SearchDataSourceImpl(prefDataSource, authenticator)

    @Singleton
    @Provides
    fun providerGetMusicRepo(searchDataSource: SearchDataSource): GetMusicRepository =
        GetMusicRepoImpl(searchDataSource)

    @Singleton
    @Provides
    fun providerGetMusicUseCase(getMusicRepo: GetMusicRepository): GetMusicUseCase =
        GetMusicUseCaseImpl(getMusicRepo)
}