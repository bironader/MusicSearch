package com.bironader.musicsearch.framework.datasource.remote

import com.bironader.musicsearch.Constant
import com.bironader.musicsearch.Constant.LIMIT
import com.bironader.musicsearch.Constant.QUERY
import com.bironader.musicsearch.Constant.SEARCH
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH)
    suspend fun searchForMusic(
        @Query(QUERY) query: String,
        @Query(LIMIT) limit: Int = 20
    ): List<Music>
}