package com.bironader.musicsearch.injection

import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.busniness.repositories.impl.GetMusicRepoImpl
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.busniness.usecases.impl.GetMusicUseCaseImpl
import com.bironader.musicsearch.framework.datasource.remote.SearchService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.impl.SearchDataSourceImpl
import com.bironader.musicsearch.injection.Annotations.Requests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchProviderModule {

    @Singleton
    @Provides
    fun provideSearchApi(@Requests retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)


    @Singleton
    @Provides
    fun providerSearchDataSource(searchService: SearchService): SearchDataSource =
        SearchDataSourceImpl(searchService)

    @Singleton
    @Provides
    fun providerGetMusicRepo(searchDataSource: SearchDataSource): GetMusicRepository =
        GetMusicRepoImpl(searchDataSource)

    @Singleton
    @Provides
    fun providerGetMusicUseCase(getMusicRepo: GetMusicRepository): GetMusicUseCase =
        GetMusicUseCaseImpl(getMusicRepo)
}