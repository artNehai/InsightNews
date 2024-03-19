package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.ArticleResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

// Not sensitive data
private const val apiKey = "8bbfa19122374be490b93afe33f83d73"
private const val BaseUrl = "https://newsapi.org"

private interface NewsApiService {
    @GET("v2/top-headlines?country=us&apiKey=$apiKey")
    suspend fun getTopHeadlines(): ArticleResponse
}

@Singleton
class NewsRetrofitDataSource @Inject constructor() : NewsRemoteDataSource {

    private val retrofit: NewsApiService = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BaseUrl)
        .build()
        .create(NewsApiService::class.java)

    override suspend fun getTopHeadlines(): ArticleResponse =
        retrofit.getTopHeadlines()
}