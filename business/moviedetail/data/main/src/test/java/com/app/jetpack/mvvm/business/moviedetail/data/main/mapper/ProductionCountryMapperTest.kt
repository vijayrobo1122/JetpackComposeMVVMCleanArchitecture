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
            iso_3166_1 = "iso_3166_1",
            name = "name",
        )

        // When
        val productionCountry: ProductionCountry = sut.mapTo(productionCountryEntity)

        // Then
        assertEquals(productionCountryEntity.iso_3166_1, productionCountry.iso_3166_1)
        assertEquals(productionCountryEntity.name, productionCountry.name)
    }

}
