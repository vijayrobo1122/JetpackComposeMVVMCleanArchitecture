package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class MovieItemState(
    override val id: String = "",
    val movieId: Int = 0,
    val voteCount: Int = 0,
    val backdropPath: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val isAdult: Boolean = false,
    val isVideo: Boolean = false,
    val genreIdsList: List<Int> = emptyList(),
) : State {
    companion object {
        val EMPTY = MovieItemState()
    }
}
