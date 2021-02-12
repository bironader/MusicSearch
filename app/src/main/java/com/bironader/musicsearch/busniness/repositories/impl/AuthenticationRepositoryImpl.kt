package com.bironader.musicsearch.busniness.repositories.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.framework.datasource.remote.abstraction.AuthDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource) :
    AuthenticationRepository {
    override suspend fun authenticate() = flow { emit(authDataSource.authenticate()) }
}