package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.google.gson.annotations.SerializedName

data class SpokenLanguageEntity(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val isoName: String,
    @SerializedName("name")
    val name: String
)
