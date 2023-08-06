package com.sevenpeakssoftware.article_ui

import com.sevenpeakssoftware.article_ui.vo.ArticleVO

sealed interface ArticleUiState {
    object Loading : ArticleUiState
    data class Articles(
        val items: List<ArticleVO>
    ) : ArticleUiState
    data class Error(
        val errorMessage: String
    ) : ArticleUiState
}
