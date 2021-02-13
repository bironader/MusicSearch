package com.bironader.musicsearch.framework.datasource.remote.abstraction

import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlinx.coroutines.flow.Flow


interface SearchDataSource {

    suspend fun searchForMusic(
       query: String
    ): Flow<List<MusicDomainModel>>
}