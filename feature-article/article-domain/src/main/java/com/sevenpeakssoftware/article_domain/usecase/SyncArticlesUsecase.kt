package com.sevenpeakssoftware.article_domain.usecase

import com.sevenpeakssoftware.article_domain.repository.ArticleRepository
import com.sevenpeakssoftware.core.exception.SyncDataNotSuccessException
import com.sevenpeakssoftware.core.usecase.FlowCoroutineUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SyncArticlesUsecase @Inject constructor(
    private val articleRepository: ArticleRepository
) : FlowCoroutineUseCase<Unit, Unit>() {

    override fun provide(param: Unit): Flow<Unit> {
        val result = kotlin.runCatching {
            articleRepository.requestArticles()
        }
        return if (result.isSuccess) {
            flowOf(Unit)
        } else {
            throw SyncDataNotSuccessException()
        }
    }
}
