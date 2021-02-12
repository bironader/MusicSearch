package com.bironader.musicsearch.busniness.usecases.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.AuthenticationRepository
import com.bironader.musicsearch.busniness.usecases.abstraction.AuthenticationUseCase
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AuthenticationUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val prefDataSource: PrefDataSource
) : AuthenticationUseCase {
    override suspend fun authenticate(): Flow<Authentication> {
        val token = prefDataSource.getToken()
        return if (token.isEmpty()) authenticationRepository.authenticate() else flowOf(
            Authentication(token, null)
        )

    }


}