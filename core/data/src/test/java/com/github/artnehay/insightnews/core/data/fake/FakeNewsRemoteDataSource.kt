package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.NetworkArticle

object FakeNewsRemoteDataSource : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): List<NetworkArticle> =
        listOf(FakeNetworkArticle1, FakeNetworkArticle2)

    override suspend fun getHeadlinesInCategory(categoryUrlPath: String): List<NetworkArticle> =
        listOf(FakeNetworkArticle1, FakeNetworkArticle2)

    override suspend fun getAllArticles(): List<NetworkArticle> =
        listOf(FakeNetworkArticle1, FakeNetworkArticle2)
}