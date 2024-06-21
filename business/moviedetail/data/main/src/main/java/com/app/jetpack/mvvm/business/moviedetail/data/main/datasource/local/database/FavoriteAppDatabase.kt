package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.jetpack.mvvm.business.moviedetail.data.entity.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteAppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "favorites_db"
    }
}
