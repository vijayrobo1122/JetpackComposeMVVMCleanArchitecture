package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.google.gson.annotations.SerializedName

data class GenresEntity(
    @SerializedName("genres")
    val genres: List<GenreEntity>
)
