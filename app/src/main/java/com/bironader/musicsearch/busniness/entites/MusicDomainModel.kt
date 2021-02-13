package com.bironader.musicsearch.busniness.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicDomainModel(
    var id : Int,
    var title: String,
    var type: String,
    var artist: String,
    var publishingDate : String,
    var smallImage: String,
    var largeImage: String
) :Parcelable