package com.sevenpeakssoftware.core_cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.core_cache.dao.ArticleDao
import com.sevenpeakssoftware.core_cache.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], exportSchema = true, version = 1)
abstract class CarRoomDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
