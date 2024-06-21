package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class ProductionCompanyState(
    override val id: String = "",
    val productionCompanyId: Int = 0,
    val logoPath: String = "",
    val name: String = "",
    val originCountry: String = "",
) : State {
    companion object {
        val EMPTY = ProductionCompanyState()
    }
}
