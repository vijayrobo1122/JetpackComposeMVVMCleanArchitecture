package com.app.jetpack.mvvm.business.moviedetail.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_entity")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
)
