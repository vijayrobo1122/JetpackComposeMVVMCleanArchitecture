package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.SpokenLanguageEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class SpokenLanguageMapperTest {
    private val sut = SpokenLanguageMapper()

    @Test
    fun `Given a valid SpokenLanguageEntity When mapping is done Then a valid SpokenLanguage should be created`() {

        // Given
        val spokenLanguageEntity = SpokenLanguageEntity(
            englishName = "englishName",
            isoName = "isoName",
            name = "name",
        )

        // When
        val spokenLanguageModel: SpokenLanguage = sut.mapTo(spokenLanguageEntity)

        // Then
        assertEquals(spokenLanguageEntity.englishName, spokenLanguageModel.englishName)
        assertEquals(spokenLanguageEntity.isoName, spokenLanguageModel.isoName)
        assertEquals(spokenLanguageEntity.name, spokenLanguageModel.name)
    }
}

