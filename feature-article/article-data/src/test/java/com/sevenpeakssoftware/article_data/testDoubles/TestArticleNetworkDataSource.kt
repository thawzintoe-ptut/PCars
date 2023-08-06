package com.sevenpeakssoftware.article_data.testDoubles

import com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource
import com.sevenpeakssoftware.article_data.model.ArticleData

class TestArticleNetworkDataSource : ArticleNetworkDataSource {
    private val networkArticles = listOf(
        ArticleData(
            id = 1,
            publishDate = "25.05.2018 14:13",
            title = "Thaw",
            image = "articleImage123.jpg",
            ingress = "test ingress"
        )
    )

    override fun getCars(): List<ArticleData> = networkArticles
}
