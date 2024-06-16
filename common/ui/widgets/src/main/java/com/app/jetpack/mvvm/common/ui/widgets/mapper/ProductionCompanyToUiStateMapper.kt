package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.moviedetail.ProductionCompany
import com.app.jetpack.mvvm.common.ui.widgets.model.ProductionCompanyState
import javax.inject.Inject

class ProductionCompanyToUiStateMapper @Inject constructor() {

    fun map(param: ProductionCompany): ProductionCompanyState {
        return ProductionCompanyState(
            id = param.id.toString(),
            productionCompanyId = param.id,
            logoPath = param.logoPath,
            name = param.name,
            originCountry = param.originCountry,
        )
    }
}
