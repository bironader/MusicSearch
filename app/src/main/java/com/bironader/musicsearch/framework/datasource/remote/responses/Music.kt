package com.bironader.musicsearch.framework.datasource.remote.responses


import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("additionalArtists")
    val additionalArtists: List<AdditionalArtist>?,
    @SerializedName("adfunded")
    val adfunded: Boolean?,
    @SerializedName("bundleOnly")
    val bundleOnly: Boolean?,
    @SerializedName("cover")
    val cover: Cover?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("extendedMetaData")
    val extendedMetaData: ExtendedMetaData?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("idBag")
    val idBag: IdBag?,
    @SerializedName("mainArtist")
    val mainArtist: MainArtist?,
    @SerializedName("publishingDate")
    val publishingDate: String?,
    @SerializedName("release")
    val release: Release?,
    @SerializedName("statistics")
    val statistics: Statistics?,
    @SerializedName("streamable")
    val streamable: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("trackNumber")
    val trackNumber: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("volumeNumber")
    val volumeNumber: Int?
)