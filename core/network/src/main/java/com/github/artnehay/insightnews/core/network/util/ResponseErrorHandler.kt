package com.github.artnehay.insightnews.core.network.util

import com.github.artnehay.insightnews.core.network.model.ResponseErrorBody
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.io.IOException

fun <T> Response<T>.handleErrorResponse(): T {
    if (isSuccessful) {
        return body() ?: throw NewsApiException("Empty response body")
    }

    val errorBody = errorBody()?.string()
    if (errorBody.isNullOrBlank()) throw IOException()
    val decodedError =
        Json.decodeFromString(
            deserializer = ResponseErrorBody.serializer(),
            string = errorBody,
        )
    throw NewsApiException(message = decodedError.message, code = code())
}