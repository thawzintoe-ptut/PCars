package com.sevenpeakssoftware.article_data

import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource
import com.sevenpeakssoftware.article_data.testDoubles.TestArticleCacheDataSource
import com.sevenpeakssoftware.article_data.testDoubles.TestArticleNetworkDataSource
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ArticleDataSourceTest {
    private lateinit var cacheDataSource: ArticleCacheDataSource
    private lateinit var networkDataSource: ArticleNetworkDataSource
    private lateinit var articleRepositoryImpl: ArticleRepositoryImpl

    @Before
    fun setUp() {
        cacheDataSource = TestArticleCacheDataSource()
        networkDataSource = TestArticleNetworkDataSource()
        articleRepositoryImpl = ArticleRepositoryImpl(
            networkDataSource,
            cacheDataSource
        )
    }

    @Test
    fun requestArticle_from_cache_fetch_successData() = runTest {
        val result = articleRepositoryImpl.getArticles().first()
        assertNotNull(result)
    }

    @Test
    fun requestArticle_from_network_fetch_successData(): TestResult = runTest { val result = articleRepositoryImpl.requestArticles()
        assertNotNull(result)
    }
}
