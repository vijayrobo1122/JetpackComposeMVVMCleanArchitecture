package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCountryEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.SpokenLanguageEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductionCountryMapperTest {

    private val sut = ProductionCountryMapper()

    @Test
    fun `Given a valid ProductionCountryEntity When mapping is done Then a valid ProductionCountry should be created`() {

        // Given
        val productionCountryEntity = ProductionCountryEntity(
            isoName = "isoName",
            name = "name",
        )

        // When
        val productionCountry: ProductionCountry = sut.mapTo(productionCountryEntity)

        // Then
        assertEquals(productionCountryEntity.isoName, productionCountry.isoName)
        assertEquals(productionCountryEntity.name, productionCountry.name)
    }

}
