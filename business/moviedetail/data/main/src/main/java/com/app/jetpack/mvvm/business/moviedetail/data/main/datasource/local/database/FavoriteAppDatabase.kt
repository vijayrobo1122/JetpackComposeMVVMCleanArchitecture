package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.GenreDao

@Database(entities = [FavoriteEntity::class, GenreEntity::class], version = 1, exportSchema = false)
abstract class FavoriteAppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    abstract fun genreDao(): GenreDao

    companion object {
        const val DATABASE_NAME = "favorites_db"
    }
}
