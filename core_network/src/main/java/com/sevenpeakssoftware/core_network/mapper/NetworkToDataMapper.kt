package com.sevenpeakssoftware.core_network.mapper

import com.sevenpeakssoftware.article_data.model.ArticleData
import com.sevenpeakssoftware.core_network.model.ArticleContentResponse

object NetworkToDataMapper {
    fun map(item: ArticleContentResponse): ArticleData {
        return ArticleData(
            id = item.id,
            publishDate = item.dateTime ?: "",
            image = item.image ?: "",
            title = item.title ?: "",
            ingress = item.ingress ?: ""
        )
    }
}
