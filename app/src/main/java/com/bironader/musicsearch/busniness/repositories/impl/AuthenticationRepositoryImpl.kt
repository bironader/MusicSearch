package com.bironader.musicsearch.busniness.repositories.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.framework.datasource.remote.abstraction.AuthDataSource
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dataStoreSource: PrefDataSource
) :
    AuthenticationRepository {
    override suspend fun authenticate() =
        flow { emit(authDataSource.authenticate()) }.onEach {
            if (it.accessToken != null) dataStoreSource.saveToken(it.accessToken)
        }.flowOn(IO)
}