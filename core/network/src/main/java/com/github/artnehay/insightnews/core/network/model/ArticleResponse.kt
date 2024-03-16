package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val networkArticles: List<NetworkArticle>,
)