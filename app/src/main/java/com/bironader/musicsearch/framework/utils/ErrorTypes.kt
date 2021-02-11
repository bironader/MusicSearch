package com.bironader.musicsearch.framework.utils

import android.content.Context
import com.bironader.musicsearch.R
import com.bironader.musicsearch.framework.utils.ErrorTypes.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class ErrorTypes {
    object NetworkError : ErrorTypes()
    data class HttpError(val throwable: Throwable) : ErrorTypes()
    object TimeOut : ErrorTypes()
    object UnKnown : ErrorTypes()
}

fun Throwable.getType(): ErrorTypes =
    when (this) {
        is ConnectException -> NetworkError
        is UnknownHostException -> NetworkError
        is HttpException -> HttpError(this)
        is TimeoutException -> TimeOut
        else -> UnKnown
    }


fun ErrorTypes.getMessage(context: Context): String =
    when (this) {
        is NetworkError -> context.getString(R.string.connection_failed)
        is TimeOut -> context.getString(R.string.retry)
        is HttpError -> getHttpError(throwable as HttpException)
        else -> context.getString(R.string.unknown_error)

    }

fun getHttpError(throwable: HttpException): String {
    var message = throwable.message()
    val gson = Gson()
    val type = object : TypeToken<Error>() {}.type
    throwable.response()?.errorBody()?.let {
        val errorResponse = gson.fromJson<Error>(it.string(), type)
//        message = errorResponse.fault.faultstring
    }
    return message
}