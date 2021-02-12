package com.bironader.musicsearch.busniness.usecases.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.busniness.usecases.abstraction.GetMusicUseCase
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMusicUseCaseImpl @Inject constructor(private val getMusicRepository: GetMusicRepository) :
    GetMusicUseCase {
    override suspend fun getMusics(query: String) = getMusicRepository.getMusic(query)


}