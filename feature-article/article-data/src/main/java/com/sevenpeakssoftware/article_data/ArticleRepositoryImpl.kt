package com.sevenpeakssoftware.article_data

import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource
import com.sevenpeakssoftware.article_data.mapper.ArticleDataToDomainMapper
import com.sevenpeakssoftware.article_domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleNetworkDataSource: ArticleNetworkDataSource,
    private val articleCacheDataSource: ArticleCacheDataSource
) : ArticleRepository {
    override fun getArticles(): Flow<List<com.sevenpeakssoftware.article_domain.model.ArticleModel>> =
        articleCacheDataSource.getArticlesFromCache().map {
            it.map(ArticleDataToDomainMapper::mapCarsToDomain)
        }

    override fun requestArticles() {
        val networkResult = articleNetworkDataSource.getCars()
        articleCacheDataSource.putArticlesToCache(networkResult)
    }
}
