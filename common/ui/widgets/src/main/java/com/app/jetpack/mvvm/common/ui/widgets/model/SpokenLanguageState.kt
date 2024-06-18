package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class SpokenLanguageState(
    override val id: String = "",
    val englishName: String = "",
    val isoName: String = "",
    val name: String = "",
) : State {
    companion object {
        val EMPTY = SpokenLanguageState()
    }
}
