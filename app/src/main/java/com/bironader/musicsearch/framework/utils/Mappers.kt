package com.bironader.musicsearch.framework.utils

import com.bironader.musicsearch.busniness.entites.MusicDomainModel
import com.bironader.musicsearch.framework.datasource.remote.responses.Music
import kotlin.random.Random

fun Music.toDomainModel() = MusicDomainModel(
    id = this.id ?: Random(100).nextInt(),
    title = this.title ?: "",
    type = this.type ?: "",
    publishingDate = this.publishingDate?.split("T")?.get(0) ?: "",
    artist = this.mainArtist?.name ?: "",
    smallImage = "http:${this.cover?.small}",
    largeImage = "http:${this.cover?.large}"

)