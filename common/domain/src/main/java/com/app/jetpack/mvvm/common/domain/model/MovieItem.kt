package com.app.jetpack.mvvm.common.domain.model

data class MovieItem(
    val id: Int,
    val voteCount: Int,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val isAdult: Boolean,
    val isVideo: Boolean,
    val genreIdsList: List<Int>,
)
