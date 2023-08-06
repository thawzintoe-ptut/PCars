package com.sevenpeakssoftware.core_cache.mapper

import com.sevenpeakssoftware.article_data.model.ArticleData
import com.sevenpeakssoftware.core_cache.entity.ArticleEntity

object DatabaseToDataMapper {
    fun mapArticleToDataMapper(articleEntity: ArticleEntity): ArticleData {
        return ArticleData(
            id = articleEntity.articleId,
            image = articleEntity.articleImage ?: "",
            publishDate = articleEntity.publishDate,
            title = articleEntity.title ?: "",
            ingress = articleEntity.ingress ?: ""
        )
    }

    fun mapArticleToCacheMapper(carData: ArticleData): ArticleEntity {
        return ArticleEntity(
            articleId = carData.id,
            articleImage = carData.image,
            publishDate = carData.publishDate,
            title = carData.title,
            ingress = carData.ingress
        )
    }
}
