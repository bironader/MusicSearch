package com.bironader.musicsearch.framework.datasource.remote.impl

import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.AuthDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Authentication
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) :
    AuthDataSource {
    override suspend fun authenticate() = authService.authenticate()
}