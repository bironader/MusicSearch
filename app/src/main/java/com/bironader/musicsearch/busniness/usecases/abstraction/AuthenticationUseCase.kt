package com.bironader.musicsearch.busniness.usecases.abstraction

import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {
    suspend fun authenticate(): Flow<Authentication>

}