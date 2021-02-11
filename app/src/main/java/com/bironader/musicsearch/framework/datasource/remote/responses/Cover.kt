package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class Cover(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("tiny")
    val tiny: String?
)