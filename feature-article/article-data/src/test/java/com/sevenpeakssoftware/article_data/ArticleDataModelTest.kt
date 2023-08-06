package com.sevenpeakssoftware.article_data

import com.sevenpeakssoftware.article_data.mapper.ArticleDataToDomainMapper
import com.sevenpeakssoftware.article_data.model.ArticleData
import org.junit.Assert
import org.junit.Test

class ArticleDataModelTest {
    @Test
    fun dataArticle_can_be_mapped_to_domainArticle() {
        val dataArticle = ArticleData(
            id = 1,
            image = "123.jpg",
            publishDate = "",
            title = "Car",
            ingress = "Car Ingress"
        )
        val domainArticle = ArticleDataToDomainMapper.mapCarsToDomain(dataArticle)
        Assert.assertEquals(1, domainArticle.id)
        Assert.assertEquals("Car", domainArticle.title)
        Assert.assertEquals("Car Ingress", domainArticle.ingress)
    }
}
