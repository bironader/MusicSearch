package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class MainArtist(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("role")
    val role: String?
)