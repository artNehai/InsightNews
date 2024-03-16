package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.ArticleResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val apiKey = "8bbfa19122374be490b93afe33f83d73"
private const val BaseUrl = "https://newsapi.org"

private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BaseUrl)
    .build()

sealed interface NewsApiService {
    @GET("v2/top-headlines?country=us&apiKey=$apiKey")
    suspend fun getTopHeadlines(): ArticleResponse
}

val newsApiService: NewsApiService by lazy {
    retrofit.create(NewsApiService::class.java)
}