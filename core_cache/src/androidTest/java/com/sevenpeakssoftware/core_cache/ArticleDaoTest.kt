package com.sevenpeakssoftware.core_cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sevenpeakssoftware.core_cache.dao.ArticleDao
import com.sevenpeakssoftware.core_cache.entity.ArticleEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ArticleDaoTest {
    private lateinit var articleDao: ArticleDao
    private lateinit var database: CarRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            CarRoomDatabase::class.java
        ).build()
        articleDao = database.articleDao()
    }

    @Test
    fun articleDao_fetches_items_return_correctAllArticles() = runTest {
        val newArticles = listOf(
            ArticleEntity(
                articleId = 1,
                publishDate = "25.05.2018 14:13",
                title = "Thaw",
                articleImage = "articleImage123.jpg",
                ingress = "test ingress"
            )
        )
        articleDao.insertAll(newArticles)
        val savedArticle = articleDao.getAllArticles().first()
        assertEquals(
            newArticles,
            savedArticle.map { it }
        )
    }

    @Test
    fun articleDao_fetches_items_by_descending_articleId() = runTest {
        val newArticles = listOf(
            ArticleEntity(
                articleId = 1,
                publishDate = "25.05.2018 14:13",
                title = "",
                articleImage = "",
                ingress = ""
            ),
            ArticleEntity(
                articleId = 2,
                publishDate = "",
                title = "",
                articleImage = "",
                ingress = ""
            ),
            ArticleEntity(
                articleId = 3,
                publishDate = "",
                title = "",
                articleImage = "",
                ingress = ""
            )
        )
        articleDao.insertAll(newArticles)
        val savedArticles = articleDao.getAllArticles().first()
        assertEquals(
            listOf(3, 2, 1),
            savedArticles.map { it.articleId }
        )
    }
}
