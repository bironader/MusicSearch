package com.bironader.musicsearch.framework.datasource.remote.impl

import com.bironader.musicsearch.framework.datasource.remote.AuthService
import com.bironader.musicsearch.framework.datasource.remote.SearchService
import com.bironader.musicsearch.framework.datasource.remote.abstraction.SearchDataSource
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val searchService: SearchService) :
    SearchDataSource {
    override suspend fun searchForMusic(query: String, limit: Int) =
        searchService.searchForMusic(query, limit)
}