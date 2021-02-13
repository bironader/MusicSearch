package com.bironader.musicsearch.framework.utils

import android.content.Context
import android.util.Log
import com.bironader.musicsearch.R
import com.bironader.musicsearch.framework.utils.ErrorTypes.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class ErrorTypes {
    object NetworkError : ErrorTypes()
    object TimeOut : ErrorTypes()
    object ConnectException : ErrorTypes()
    object UnknownError : ErrorTypes()
}

fun Throwable.getType(): ErrorTypes =
    when (this) {
        is ConnectException -> NetworkError
        is UnknownHostException -> NetworkError
        is TimeoutException -> TimeOut
        else -> UnknownError
    }


fun ErrorTypes.getMessage(context: Context): String =
    when (this) {
        is NetworkError -> context.getString(R.string.connection_failed)
        is ConnectException -> context.getString(R.string.connection_failed)
        is TimeOut -> context.getString(R.string.retry)
        else -> context.getString(R.string.unknown_error)

    }

