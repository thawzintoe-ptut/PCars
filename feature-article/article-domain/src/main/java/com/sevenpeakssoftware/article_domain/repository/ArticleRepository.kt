package com.sevenpeakssoftware.article_domain.repository

import com.sevenpeakssoftware.article_domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<List<ArticleModel>>
    fun requestArticles()
}