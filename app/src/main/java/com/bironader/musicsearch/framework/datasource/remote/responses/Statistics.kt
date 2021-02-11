package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("estimatedRecentCount")
    val estimatedRecentCount: Int?,
    @SerializedName("estimatedTotalCount")
    val estimatedTotalCount: Int?,
    @SerializedName("popularity")
    val popularity: Int?
)