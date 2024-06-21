package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class MovieDetailState(
    override val id: String = "",
    val movieId: Int = 0,
    val budget: Int = 0,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val voteCount: Int = 0,
    val backdropPath: String = "",
    val homepage: String = "",
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val isAdult: Boolean = false,
    val isVideo: Boolean = false,
    val belongsToCollection: BelongsToCollectionState = BelongsToCollectionState.EMPTY,
    val genresList: List<GenreState> = emptyList(),
    val productionCompaniesList: List<ProductionCompanyState> = emptyList(),
    val productionCountriesList: List<ProductionCountryState> = emptyList(),
    val spokenLanguagesList: List<SpokenLanguageState> = emptyList(),
) : State {
    companion object {
        val EMPTY = MovieDetailState()
    }
}
