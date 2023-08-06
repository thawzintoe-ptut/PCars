package com.sevenpeakssoftware.article_domain

import com.sevenpeakssoftware.article_domain.model.ArticleModel
import com.sevenpeakssoftware.article_domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleRepositoryFake constructor(
    private val shouldReturnError: Boolean
) : ArticleRepository {
    private val articlesModelFlow = MutableStateFlow(
        listOf(
            ArticleModel(
                publishDate = "25.05.2018 14:13",
                title = "Thaw",
                image = "articleImage123.jpg",
                ingress = "test ingress",
                id = 1
            )
        )
    )

    override fun getArticles(): Flow<List<ArticleModel>> =
        articlesModelFlow

    override fun requestArticles() {
        throw NotImplementedError("Unused in tests")
    }
}
