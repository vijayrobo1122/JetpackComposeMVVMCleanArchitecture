package com.app.jetpack.mvvm.common.data.mapper.moviesdetail

import com.app.jetpack.mvvm.common.data.entity.moviedetail.ProductionCompanyEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.moviedetail.ProductionCompany
import javax.inject.Inject

class ProductionCompanyMapper @Inject constructor() :
    Mapper<ProductionCompanyEntity, ProductionCompany> {

    override fun mapTo(type: ProductionCompanyEntity?): ProductionCompany {
        return ProductionCompany(
            id = type?.id ?: 0,
            logoPath = type?.logo_path.orEmpty(),
            name = type?.name.orEmpty(),
            originCountry = type?.origin_country.orEmpty(),
        )
    }
}
