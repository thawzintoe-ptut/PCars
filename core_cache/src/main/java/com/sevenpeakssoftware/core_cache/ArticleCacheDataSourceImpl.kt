package com.sevenpeakssoftware.core_cache

import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.article_data.model.ArticleData
import com.sevenpeakssoftware.core_cache.dao.ArticleDao
import com.sevenpeakssoftware.core_cache.mapper.DatabaseToDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleCacheDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao
) : ArticleCacheDataSource {
    override fun getArticlesFromCache(): Flow<List<ArticleData>> =
        articleDao.getAllArticles().map {
            it.map(DatabaseToDataMapper::mapArticleToDataMapper)
        }

    override fun putArticlesToCache(articles: List<ArticleData>) =
        articleDao.insertAll(articles.map(DatabaseToDataMapper::mapArticleToCacheMapper))
}
