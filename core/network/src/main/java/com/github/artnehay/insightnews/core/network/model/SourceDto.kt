package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String? = "",
    val name: String? = "",
    val description: String? = "",
    val url: String? = "",
    val category: String? = "",
    val language: String? = "",
    val country: String? = "",
)