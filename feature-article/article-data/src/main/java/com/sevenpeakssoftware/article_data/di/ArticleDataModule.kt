package com.sevenpeakssoftware.article_data.di

import com.sevenpeakssoftware.article_data.ArticleRepositoryImpl
import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleDataModule {
    @Provides
    @Singleton
    fun provideCarsRepository(
        homeNetworkDataSource: ArticleNetworkDataSource,
        articleCacheDataSource: ArticleCacheDataSource
    ): com.sevenpeakssoftware.article_domain.repository.ArticleRepository {
        return ArticleRepositoryImpl(homeNetworkDataSource, articleCacheDataSource)
    }
}
