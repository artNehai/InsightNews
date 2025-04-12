package com.github.artnehay.insightnews.core.network.di

import com.github.artnehay.insightnews.core.network.ArticleRemoteDataSource
import com.github.artnehay.insightnews.core.network.ArticleRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {

    @Binds
    abstract fun bindNewsRemoteDataSource(
        articleRemoteDataSource: ArticleRemoteDataSourceImpl,
    ): ArticleRemoteDataSource
}