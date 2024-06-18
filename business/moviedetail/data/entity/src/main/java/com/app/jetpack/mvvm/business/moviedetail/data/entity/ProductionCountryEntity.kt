package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.google.gson.annotations.SerializedName

data class ProductionCountryEntity(
    @SerializedName("iso_3166_1")
    val isoName: String,
    @SerializedName("name")
    val name: String
)
