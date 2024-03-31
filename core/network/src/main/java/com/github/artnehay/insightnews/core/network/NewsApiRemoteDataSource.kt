package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import com.github.artnehay.insightnews.core.network.model.NetworkArticleResponse
import com.github.artnehay.insightnews.core.network.util.handleErrorResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

// Non-sensitive data
private const val apiKey = "8bbfa19122374be490b93afe33f83d73"
private const val BaseUrl = "https://newsapi.org"

private interface NewsApiService {
    @GET("v2/top-headlines?country=us&apiKey=$apiKey")
    suspend fun getTopHeadlines(): Response<NetworkArticleResponse>
}

class NewsApiRemoteDataSource @Inject constructor() : NewsRemoteDataSource {

    private val retrofit: NewsApiService = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BaseUrl)
        .build()
        .create(NewsApiService::class.java)

    override suspend fun getTopHeadlines(): List<NetworkArticle> =
        retrofit.getTopHeadlines().handleErrorResponse().articles
}