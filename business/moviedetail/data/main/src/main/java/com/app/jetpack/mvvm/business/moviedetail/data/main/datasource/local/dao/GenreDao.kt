package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre_entity")
    suspend fun getAllData(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genreEntity: GenreEntity)

    @Query("DELETE FROM genre_entity WHERE genreId = :genreId")
    suspend fun deleteGenre(genreId: Int)
}
