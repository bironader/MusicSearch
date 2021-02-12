package com.bironader.musicsearch.framework.utils

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music

fun Music.toDomainModel() = MusicDomainModel(
    title = this.title ?: "",
    type = this.type ?: "",
    artist = this.mainArtist?.name ?: "",
    smallImage = "http${this.cover?.small}",
    largeImage = "http${this.cover?.large}"

)