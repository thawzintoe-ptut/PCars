package com.sevenpeakssoftware.article_data.mapper

import com.sevenpeakssoftware.article_data.model.ArticleData
import com.sevenpeakssoftware.article_domain.model.ArticleModel

object ArticleDataToDomainMapper {
    fun mapCarsToDomain(carsData: ArticleData): ArticleModel {
        return ArticleModel(
            id = carsData.id,
            image = carsData.image,
            publishDate = carsData.publishDate,
            title = carsData.title,
            ingress = carsData.ingress
        )
    }
}
