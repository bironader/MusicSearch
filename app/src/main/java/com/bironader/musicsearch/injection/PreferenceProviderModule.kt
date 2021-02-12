package com.bironader.musicsearch.injection

import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.datasource.remote.impl.PrefDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceProviderModule {
    @Binds
    abstract fun bindPrefsStore(dataStoreImpl: PrefDataImpl): PrefDataSource
}