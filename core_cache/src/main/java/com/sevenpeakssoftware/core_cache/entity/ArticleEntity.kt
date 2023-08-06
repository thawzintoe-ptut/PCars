package com.sevenpeakssoftware.core_cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    var articleId: Int,
    @ColumnInfo(name = "publishDate")
    var publishDate: String,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "articleImage")
    var articleImage: String?,
    @ColumnInfo(name = "ingress")
    var ingress: String?
)
