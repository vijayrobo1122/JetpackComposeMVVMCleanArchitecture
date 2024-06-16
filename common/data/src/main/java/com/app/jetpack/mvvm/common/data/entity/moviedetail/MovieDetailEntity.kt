package com.app.jetpack.mvvm.common.data.entity.moviedetail

data class MovieDetailEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollectionEntity,
    val budget: Int,
    val genres: List<GenreEntity>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyEntity>,
    val production_countries: List<ProductionCountryEntity>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageEntity>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)