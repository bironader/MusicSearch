package com.bironader.musicsearch.busniness.repositories.abstraction

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlinx.coroutines.flow.Flow

interface GetMusicRepository {

    suspend fun getMusic(query: String): Flow<List<MusicDomainModel>>
}