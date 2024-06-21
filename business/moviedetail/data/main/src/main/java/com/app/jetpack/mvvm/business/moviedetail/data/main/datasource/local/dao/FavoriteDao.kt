package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.jetpack.mvvm.business.moviedetail.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_entity WHERE movieId = :movieId")
    suspend fun deleteFavorite(movieId: Int)

    @Query("SELECT COUNT(*) FROM favorite_entity WHERE movieId = :movieId")
    suspend fun isFavorite(movieId: Int): Int
}
