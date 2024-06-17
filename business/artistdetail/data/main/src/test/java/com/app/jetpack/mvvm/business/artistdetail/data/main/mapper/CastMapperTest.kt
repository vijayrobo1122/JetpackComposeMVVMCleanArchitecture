package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.CastEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class CastMapperTest {

    private val sut = CastMapper()

    @Test
    fun `Given a valid cast entity When mapping is done Then a valid cast model should be created`() {

        // Given
        val castEntity = CastEntity(
            id = 123,
            castId = 123,
            gender = 1,
            order = 4,
            adult = true,
            creditId = "creditId",
            character = "character",
            knownForDepartment = "knownForDepartment",
            name = "name",
            originalName = "originalName",
            profilePath = "profilePath",
            popularity = 8.5,
        )

        // When
        val castModel = sut.mapTo(castEntity)

        // Then
        assertEquals(castEntity.id, castModel.id)
        assertEquals(castEntity.gender, castModel.gender)
        assertEquals(castEntity.adult, castModel.isAdult)
        assertEquals(castEntity.creditId, castModel.creditId)
        assertEquals(castEntity.character, castModel.character)
        assertEquals(castEntity.order, castModel.order)
        assertEquals(castEntity.knownForDepartment, castModel.knownForDepartment)
        assertEquals(castEntity.name, castModel.name)
        assertEquals(castEntity.originalName, castModel.originalName)
        assertEquals(castEntity.profilePath, castModel.profilePath)
    }
}
