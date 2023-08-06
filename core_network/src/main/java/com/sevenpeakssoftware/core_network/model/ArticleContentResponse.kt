package com.sevenpeakssoftware.core_network.model

import com.squareup.moshi.Json

data class ArticleContentResponse(
    @field:Json(name = "changed")
    val changed: Int? = 0,
    @field:Json(name = "content")
    val content: List<ArticleItemResponse>,
    @field:Json(name = "created")
    val created: Int? = 0,
    @field:Json(name = "dateTime")
    val dateTime: String? = "",
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image")
    val image: String? = "",
    @field:Json(name = "ingress")
    val ingress: String? = "",
    @field:Json(name = "title")
    val title: String? = ""
) {
    companion object {
        val dummy by lazy {
            ArticleContentResponse(
                changed = 1534311497,
                created = 1511968425,
                content = listOf(
                    ArticleItemResponse.dummy
                ),
                dateTime = "25.05.2018 14:13",
                id = 1,
                image = "audi_q7.jpg",
                ingress = "test ingress",
                title = "Thaw"
            )
        }
    }
}
