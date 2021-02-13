package com.bironader.musicsearch.busniness.repositories.impl

import com.bironader.musicsearch.busniness.repositories.abstraction.GetMusicRepository
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetMusicRepoImpl @Inject constructor(private val searchDataSource: SearchDataSource) :
    GetMusicRepository {
    override suspend fun getMusic(query: String) =
        searchDataSource.searchForMusic(query)
}