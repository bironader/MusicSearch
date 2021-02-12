package com.bironader.musicsearch.busniness.usecases.abstraction

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlinx.coroutines.flow.Flow

interface GetMusicUseCase {
    suspend fun getMusics(query: String): Flow<List<MusicDomainModel>>
}