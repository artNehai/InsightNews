package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.ArticleResponse

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(): ArticleResponse
}