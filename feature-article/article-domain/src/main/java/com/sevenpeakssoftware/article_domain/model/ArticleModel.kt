package com.sevenpeakssoftware.article_domain.model

data class ArticleModel(
    val id: Int,
    val image: String,
    val publishDate: String,
    val title: String,
    val ingress: String
)