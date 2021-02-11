package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class ExtendedMetaData(
    @SerializedName("genresHierarchy")
    val genresHierarchy: List<String>?,
    @SerializedName("gracenoteRythmApiGenreIds")
    val gracenoteRythmApiGenreIds: String?,
    @SerializedName("languages")
    val languages: String?,
    @SerializedName("moods")
    val moods: List<String>?,
    @SerializedName("originalSongId")
    val originalSongId: String?,
    @SerializedName("originalTitle")
    val originalTitle: String?,
    @SerializedName("releaseYear")
    val releaseYear: String?,
    @SerializedName("tempos")
    val tempos: List<String>?
)