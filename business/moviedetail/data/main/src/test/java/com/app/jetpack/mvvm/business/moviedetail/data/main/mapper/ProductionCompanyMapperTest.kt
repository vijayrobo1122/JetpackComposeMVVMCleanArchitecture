package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCompanyEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCountryEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCompany
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductionCompanyMapperTest {

    private val sut = ProductionCompanyMapper()

    @Test
    fun `Given a valid ProductionCompanyEntity When mapping is done Then a valid ProductionCompany should be created`() {

        // Given
        val productionCompanyEntity = ProductionCompanyEntity(
            id = 234,
            logo_path = "logo_path",
            origin_country = "origin_country",
            name = "name",
        )

        // When
        val productionCompany: ProductionCompany = sut.mapTo(productionCompanyEntity)

        // Then
        assertEquals(productionCompanyEntity.id, productionCompany.id)
        assertEquals(productionCompanyEntity.logo_path, productionCompany.logoPath)
        assertEquals(productionCompanyEntity.origin_country, productionCompany.originCountry)
        assertEquals(productionCompanyEntity.name, productionCompany.name)
    }

}

