package com.bironader.musicsearch.busniness.usecases.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.busniness.usecases.abstraction.AuthenticationUseCase
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationUseCaseImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    AuthenticationUseCase {
    override suspend fun authenticate(): Flow<Authentication> {
        TODO("Not yet implemented")
    }
}