package com.github.artnehay.insightnews.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticle(
    val source: NetworkSourceHeader,
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
)

@Serializable
data class NetworkSourceHeader(
    val id: String = "",
    val name: String = "",
)