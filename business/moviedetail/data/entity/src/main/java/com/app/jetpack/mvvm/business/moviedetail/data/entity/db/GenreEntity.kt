package com.app.jetpack.mvvm.business.moviedetail.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre_entity")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("primary_key_id")
    val id: Int = 0,
    @SerializedName("id")
    val genreId: Int,
    @SerializedName("name")
    val name: String,
)
