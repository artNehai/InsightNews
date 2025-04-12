package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>,
)