package com.sevenpeakssoftware.core_cache.di

import android.content.Context
import androidx.room.Room
import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.core_cache.ArticleCacheDataSourceImpl
import com.sevenpeakssoftware.core_cache.CarRoomDatabase
import com.sevenpeakssoftware.core_cache.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    fun providesArticleDao(
        database: CarRoomDatabase
    ): ArticleDao = database.articleDao()

    @Provides
    @Singleton
    fun providesCarCacheDataSource(
        articleDao: ArticleDao
    ): ArticleCacheDataSource {
        return ArticleCacheDataSourceImpl(articleDao)
    }

    @Provides
    @Singleton
    fun providesCarRoomDatabase(
        @ApplicationContext context: Context
    ): CarRoomDatabase = Room.databaseBuilder(
        context,
        CarRoomDatabase::class.java,
        "car-database"
    ).build()
}
