package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class IdBag(
    @SerializedName("roviTrackId")
    val roviTrackId: String?
)