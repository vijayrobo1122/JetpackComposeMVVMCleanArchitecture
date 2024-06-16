package com.app.jetpack.mvvm.common.data.mapper.moviesdetail

import com.app.jetpack.mvvm.common.data.entity.moviedetail.ProductionCountryEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.moviedetail.ProductionCountry
import javax.inject.Inject

class ProductionCountryMapper @Inject constructor() :
    Mapper<ProductionCountryEntity, ProductionCountry> {

    override fun mapTo(type: ProductionCountryEntity?): ProductionCountry {
        return ProductionCountry(
            iso_3166_1 = type?.iso_3166_1.orEmpty(),
            name = type?.name.orEmpty(),
        )
    }
}
