package com.sevenpeakssoftware.core_network.model

import com.squareup.moshi.Json

data class GetArticlesResponse(
    @field:Json(name = "content")
    val content: List<ArticleContentResponse>,
    @field:Json(name = "serverTime")
    val serverTime: Int? = null,
    @field:Json(name = "status")
    val status: String? = null
)
