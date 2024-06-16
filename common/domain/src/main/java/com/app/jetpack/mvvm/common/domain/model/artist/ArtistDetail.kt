package com.app.jetpack.mvvm.common.domain.model.artist

data class ArtistDetail(
    val id: Int,
    val gender: Int,
    val biography: String,
    val birthday: String,
    val homepage: String,
    val imdbId: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val profilePath: String,
    val popularity: Double,
    val isAdult: Boolean,
    val alsoKnownAsList: List<String>,
)
