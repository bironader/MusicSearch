package com.bironader.musicsearch.busniness.repositories.abstraction

import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun authenticate(): Flow<Authentication>
}