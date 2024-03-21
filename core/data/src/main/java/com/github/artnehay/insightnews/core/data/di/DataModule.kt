package com.github.artnehay.insightnews.core.data.di

import com.github.artnehay.insightnews.core.network.NewsApiRemoteDataSource
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class NewsApiDataSource

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @NewsApiDataSource
    @Singleton
    @Binds
    abstract fun bindNewsRemoteDataSource(
        newsApiRemoteDataSource: NewsApiRemoteDataSource,
    ): NewsRemoteDataSource
}