package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCompanyEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCompany
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class ProductionCompanyMapper @Inject constructor() :
    Mapper<ProductionCompanyEntity, ProductionCompany> {

    override fun mapTo(type: ProductionCompanyEntity?): ProductionCompany {
        return ProductionCompany(
            id = type?.id ?: 0,
            logoPath = type?.logoPath.orEmpty(),
            name = type?.name.orEmpty(),
            originCountry = type?.originCountry.orEmpty(),
        )
    }
}