package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.moviedetail.ProductionCountry
import com.app.jetpack.mvvm.common.ui.widgets.model.ProductionCountryState
import javax.inject.Inject

class ProductionCountryToUiStateMapper @Inject constructor() {

    fun map(param: ProductionCountry): ProductionCountryState {
        return ProductionCountryState(
            id = param.iso_3166_1,
            iso_3166_1 = param.iso_3166_1,
            name = param.name,
        )
    }
}
