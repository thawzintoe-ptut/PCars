package com.sevenpeakssoftware.article_data.dataSource

import com.sevenpeakssoftware.article_data.model.ArticleData

interface ArticleNetworkDataSource {
    fun getCars(): List<ArticleData>
}
