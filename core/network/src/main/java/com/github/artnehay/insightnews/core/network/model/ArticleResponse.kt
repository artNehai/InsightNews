package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    @SerialName("articles")
    val networkArticles: List<NetworkArticle>,
)