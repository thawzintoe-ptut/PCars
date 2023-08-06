package com.sevenpeakssoftware.core_network.model

import com.squareup.moshi.Json

data class ArticleItemResponse(
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "subject")
    val subject: String? = null,
    @field:Json(name = "type")
    val type: String? = null
) {
    companion object {
        val dummy by lazy {
            ArticleItemResponse(
                description = "test description",
                subject = "Q7",
                type = "text"
            )
        }
    }
}
