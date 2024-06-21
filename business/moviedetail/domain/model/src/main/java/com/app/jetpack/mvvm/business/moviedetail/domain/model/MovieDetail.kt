package com.app.jetpack.mvvm.business.moviedetail.domain.model

data class MovieDetail(
    val id: Int,
    val budget: Int,
    val revenue: Int,
    val runtime: Int,
    val voteCount: Int,
    val backdropPath: String,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val status: String,
    val tagline: String,
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val isAdult: Boolean,
    val isVideo: Boolean,
    val isFavorite: Boolean = false,
    val belongsToCollection: BelongsToCollection,
    val genresList: List<Genre>,
    val productionCompaniesList: List<ProductionCompany>,
    val productionCountriesList: List<ProductionCountry>,
    val spokenLanguagesList: List<SpokenLanguage>,
)
