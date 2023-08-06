package com.sevenpeakssoftware.article_data.testDoubles

import com.sevenpeakssoftware.article_data.dataSource.ArticleCacheDataSource
import com.sevenpeakssoftware.article_data.model.ArticleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TestArticleCacheDataSource : ArticleCacheDataSource {
    private val dataArticlesFlow = MutableStateFlow(
        listOf(
            ArticleData(
                id = 1,
                publishDate = "25.05.2018 14:13",
                title = "Thaw",
                image = "articleImage123.jpg",
                ingress = "test ingress"
            )
        )
    )
    override fun getArticlesFromCache(): Flow<List<ArticleData>> = dataArticlesFlow

    override fun putArticlesToCache(articles: List<ArticleData>) {
        dataArticlesFlow.value = articles
    }
}
