package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.NetworkArticle

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(): List<NetworkArticle>
    suspend fun getHeadlinesInCategory(categoryUrlPath: String): List<NetworkArticle>
}