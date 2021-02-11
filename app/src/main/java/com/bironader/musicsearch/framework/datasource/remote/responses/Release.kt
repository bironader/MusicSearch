package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class Release(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)