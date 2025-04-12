package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceHeaderDto(
    val id: String? = "",
    val name: String? = "",
)