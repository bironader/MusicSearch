package com.bironader.musicsearch.framework.datasource.remote.impl

import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.SearchService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import com.bironader.musicsearch.framework.utils.toDomainModel
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val searchService: SearchService) :
    SearchDataSource {

    override suspend fun searchForMusic(query: String) =
        searchService.searchForMusic(query).map { it.toDomainModel() }

}