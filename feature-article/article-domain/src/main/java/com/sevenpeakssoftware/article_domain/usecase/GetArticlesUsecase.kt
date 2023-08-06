package com.sevenpeakssoftware.article_domain.usecase

import com.sevenpeakssoftware.article_domain.model.ArticleModel
import com.sevenpeakssoftware.article_domain.repository.ArticleRepository
import com.sevenpeakssoftware.core.usecase.FlowCoroutineUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class GetArticlesUsecase @Inject constructor(
    private val articleRepository: ArticleRepository
) : FlowCoroutineUseCase<Unit, List<ArticleModel>>() {

    override fun provide(param: Unit): Flow<List<ArticleModel>> {
        return articleRepository.getArticles()
            .distinctUntilChanged()
    }
}
