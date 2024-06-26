package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity
import com.google.gson.annotations.SerializedName

data class MovieDetailEntity(
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionEntity,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genresList: List<GenreEntity>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompaniesList: List<ProductionCompanyEntity>,
    @SerializedName("production_countries")
    val productionCountriesList: List<ProductionCountryEntity>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguagesList: List<SpokenLanguageEntity>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val isVideo: Boolean,
    @SerializedName("favorite")
    val isFavorite: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
)
