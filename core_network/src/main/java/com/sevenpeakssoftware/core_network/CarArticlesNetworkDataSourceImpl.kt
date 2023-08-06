package com.sevenpeakssoftware.core_network

import com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource
import com.sevenpeakssoftware.article_data.model.ArticleData
import com.sevenpeakssoftware.core_network.mapper.NetworkToDataMapper
import javax.inject.Inject

class CarArticlesNetworkDataSourceImpl @Inject constructor(
    private val api: CarApi
) : ArticleNetworkDataSource {
    override fun getCars(): List<ArticleData> {
        val result = api.getCarArticles().executeOrThrow()
        return result.content.map(NetworkToDataMapper::map)
    }
}
