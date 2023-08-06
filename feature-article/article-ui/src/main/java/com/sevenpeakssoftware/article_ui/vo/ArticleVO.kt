package com.sevenpeakssoftware.article_ui.vo

import com.sevenpeakssoftware.article_domain.model.ArticleModel
import com.sevenpeakssoftware.core.mapper.UnidirectionalMap
import com.sevenpeakssoftware.core.util.toArticleFormatDateTime
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

data class ArticleVO(
    val id: Int,
    val image: String,
    val publishDate: LocalDateTime,
    val title: String,
    val ingress: String
) {
    companion object {
        val dummy by lazy {
            ArticleVO(
                id = 1,
                image = "",
                publishDate = LocalDateTime.now(),
                title = "Q7 - Greatness starts, when you don't stop.",
                ingress = "The Audi Q7 is the result of an ambitious idea: never cease to improve."
            )
        }
    }
}

class ArticleVOMapper @Inject constructor() : UnidirectionalMap<ArticleModel, ArticleVO> {
    override fun map(item: ArticleModel): ArticleVO =
        ArticleVO(
            id = item.id,
            image = item.image,
            publishDate = item.publishDate.toArticleFormatDateTime(),
            title = item.title,
            ingress = item.ingress
        )
}
