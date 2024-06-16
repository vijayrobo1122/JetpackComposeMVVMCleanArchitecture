package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class ArtistDetailState(
    override val id: String = "",
    val artistId: Int = 0,
    val gender: Int = 0,
    val biography: String = "",
    val birthday: String = "",
    val homepage: String = "",
    val imdbId: String = "",
    val knownForDepartment: String = "",
    val name: String = "",
    val placeOfBirth: String = "",
    val profilePath: String = "",
    val popularity: Double = 0.0,
    val isAdult: Boolean = false,
    val alsoKnownAsList: List<String> = emptyList(),
) : State {
    companion object {
        val EMPTY = ArtistDetailState()
    }
}
