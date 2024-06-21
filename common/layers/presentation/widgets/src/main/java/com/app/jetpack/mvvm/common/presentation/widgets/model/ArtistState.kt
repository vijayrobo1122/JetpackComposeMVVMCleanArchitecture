package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class ArtistState(
    override val id: String = "",
    val artistId: Int = 0,
    val castList: List<CastState> = emptyList(),
    val crewList: List<CrewState> = emptyList(),
) : State {
    companion object {
        val EMPTY = ArtistState()
    }
}
