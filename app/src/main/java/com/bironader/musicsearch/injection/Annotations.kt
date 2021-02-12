package com.bironader.musicsearch.injection

import javax.inject.Qualifier

object Annotations {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RefreshToken

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Requests
}