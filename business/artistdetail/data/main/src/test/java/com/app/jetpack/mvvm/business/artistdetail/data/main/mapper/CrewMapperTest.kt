package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.CrewEntity
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Crew
import org.junit.Assert.assertEquals
import org.junit.Test

class CrewMapperTest {

    private val sut = CrewMapper()

    @Test
    fun `Given a valid CrewEntity When mapping is done Then a valid Crew should be created`() {

        // Given
        val crewEntity = CrewEntity(
            id = 123,
            gender = 1,
            adult = true,
            creditId = "creditId",
            department = "department",
            job = "job",
            knownForDepartment = "knownForDepartment",
            name = "name",
            originalName = "originalName",
            profilePath = "profilePath",
            popularity = 8.5,
        )

        // When
        val crewModel: Crew = sut.mapTo(crewEntity)

        // Then
        assertEquals(crewEntity.id, crewModel.id)
        assertEquals(crewEntity.gender, crewModel.gender)
        assertEquals(crewEntity.adult, crewModel.isAdult)
        assertEquals(crewEntity.creditId, crewModel.creditId)
        assertEquals(crewEntity.department, crewModel.department)
        assertEquals(crewEntity.job, crewModel.job)
        assertEquals(crewEntity.knownForDepartment, crewModel.knownForDepartment)
        assertEquals(crewEntity.name, crewModel.name)
        assertEquals(crewEntity.originalName, crewModel.originalName)
        assertEquals(crewEntity.profilePath, crewModel.profilePath)
    }
}
