package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseErrorBody(
    val status: String,
    val code: String,
    val message: String,
)