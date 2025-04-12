package com.github.artnehay.insightnews.core.data.di

import com.github.artnehay.insightnews.core.data.ArticleRepositoryImpl
import com.github.artnehay.insightnews.core.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindArticlesRepository(
        articleRepository: ArticleRepositoryImpl,
    ): ArticleRepository
}