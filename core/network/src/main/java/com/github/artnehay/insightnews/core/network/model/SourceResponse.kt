package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    val status: String,
    val sources: List<NetworkSource>,
)