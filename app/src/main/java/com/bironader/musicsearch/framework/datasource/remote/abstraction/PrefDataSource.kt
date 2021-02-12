package com.bironader.musicsearch.framework.datasource.remote.abstraction

import kotlinx.coroutines.flow.Flow


interface PrefDataSource {

    fun saveToken(token: String)
    fun getToken(): String

}