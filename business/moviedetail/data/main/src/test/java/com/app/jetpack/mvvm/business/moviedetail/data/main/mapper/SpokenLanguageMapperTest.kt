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
            english_name = "english_name",
            iso_639_1 = "iso_639_1",
            name = "name",
        )

        // When
        val spokenLanguageModel: SpokenLanguage = sut.mapTo(spokenLanguageEntity)

        // Then
        assertEquals(spokenLanguageEntity.english_name, spokenLanguageModel.englishName)
        assertEquals(spokenLanguageEntity.iso_639_1, spokenLanguageModel.iso_639_1)
        assertEquals(spokenLanguageEntity.name, spokenLanguageModel.name)
    }
}

