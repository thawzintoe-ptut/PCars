package com.sevenpeakssoftware.article_data.dataSource

import com.sevenpeakssoftware.article_data.model.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticleCacheDataSource {
//    fun getCarsFromCache(): Flow<List<ArticleData>>
//    fun putCarsToCache(cars: List<ArticleData>)
    fun getArticlesFromCache(): Flow<List<ArticleData>>
    fun putArticlesToCache(articles: List<ArticleData>)
}