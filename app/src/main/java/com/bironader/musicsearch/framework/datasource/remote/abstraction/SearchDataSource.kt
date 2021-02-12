package com.bironader.musicsearch.framework.datasource.remote.abstraction

import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchDataSource {

    suspend fun searchForMusic(
       query: String
    ): List<MusicDomainModel>
}