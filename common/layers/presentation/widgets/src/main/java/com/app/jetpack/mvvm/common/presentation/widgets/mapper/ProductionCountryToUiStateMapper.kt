package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import com.app.jetpack.mvvm.common.presentation.widgets.model.ProductionCountryState
import javax.inject.Inject

class ProductionCountryToUiStateMapper @Inject constructor() {

    fun map(param: ProductionCountry): ProductionCountryState {
        return ProductionCountryState(
            id = param.isoName,
            isoName = param.isoName,
            name = param.name,
        )
    }
}
