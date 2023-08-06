package com.sevenpeakssoftware.article_domain

import com.google.common.truth.Truth.assertThat
import com.sevenpeakssoftware.article_domain.usecase.GetArticlesUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetArticleUsecaseTest {
    private lateinit var getArticlesUsecase: GetArticlesUsecase
    private lateinit var articleRepoFake: ArticleRepositoryFake

    @Before
    fun setUp() {
        articleRepoFake = ArticleRepositoryFake(shouldReturnError = false)
        getArticlesUsecase = GetArticlesUsecase(articleRepoFake)
    }

    @Test
    fun usecase_return_correct_articles() = runTest {
        val result = articleRepoFake.getArticles()
        val articles = getArticlesUsecase.execute(Unit)
        assertThat(articles).isEqualTo(result)
    }
}
