package com.github.artnehay.insightnews.core.data.di

import android.content.Context
import com.github.artnehay.insightnews.core.database.NewsDatabase
import com.github.artnehay.insightnews.core.network.NewsApiRemoteDataSource
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class NewsApiDataSource

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataModule {

    @NewsApiDataSource
    @Binds
    abstract fun bindNewsRemoteDataSource(
        newsApiRemoteDataSource: NewsApiRemoteDataSource,
    ): NewsRemoteDataSource
}

@InstallIn(SingletonComponent::class)
@Module
object LocalDataModule {

    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
    ) = NewsDatabase.getDatabase(context)
}