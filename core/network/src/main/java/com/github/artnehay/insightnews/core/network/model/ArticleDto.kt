package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("source")
    val sourceHeaderDto: SourceHeaderDto,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
)