package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCountryEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class ProductionCountryMapper @Inject constructor() :
    Mapper<ProductionCountryEntity, ProductionCountry> {

    override fun mapTo(type: ProductionCountryEntity?): ProductionCountry {
        return ProductionCountry(
            isoName = type?.isoName.orEmpty(),
            name = type?.name.orEmpty(),
        )
    }
}
