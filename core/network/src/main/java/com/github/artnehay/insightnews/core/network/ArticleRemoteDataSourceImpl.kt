package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.ArticleDto
import com.github.artnehay.insightnews.core.network.model.ArticleResponseDto
import com.github.artnehay.insightnews.core.network.model.CategoryDto
import com.github.artnehay.insightnews.core.network.util.Sources
import com.github.artnehay.insightnews.core.network.util.handleErrorResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

// Non-sensitive api key for showcase purposes
private const val ApiKey = "8bbfa19122374be490b93afe33f83d73"
private const val BaseUrl = "https://newsapi.org"

private interface NewsApiService {
    @GET("v2/top-headlines?country=us&apiKey=$ApiKey")
    suspend fun getTopHeadlines(): Response<ArticleResponseDto>

    @GET("v2/top-headlines?country=us&apiKey=$ApiKey")
    suspend fun getHeadlinesInCategory(
        @Query("category") categoryUrlPath: String,
    ): Response<ArticleResponseDto>

    @GET("v2/everything?language=en&apiKey=$ApiKey&sources=$Sources")
    suspend fun getAllArticles(): Response<ArticleResponseDto>
}

class ArticleRemoteDataSourceImpl @Inject constructor() : ArticleRemoteDataSource {

    private val retrofit: NewsApiService = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BaseUrl)
        .build()
        .create(NewsApiService::class.java)

    override suspend fun getTopHeadlines(): List<ArticleDto> =
        retrofit.getTopHeadlines().handleErrorResponse().articles

    override suspend fun getHeadlinesInCategory(categoryDto: CategoryDto): List<ArticleDto> =
        retrofit.getHeadlinesInCategory(categoryDto.urlPath).handleErrorResponse().articles

    override suspend fun getAllArticles(): List<ArticleDto> =
        retrofit.getAllArticles().handleErrorResponse().articles
}