package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class SpokenLanguageState(
    override val id: String = "",
    val englishName: String = "",
    val iso_639_1: String = "",
    val name: String = "",
) : State {
    companion object {
        val EMPTY = SpokenLanguageState()
    }
}
