package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.CastEntity
import com.app.jetpack.mvvm.business.artistdetail.data.entity.CrewEntity
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Cast
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Crew
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistMapperTest {

    private val castMapper: CastMapper = mockk(relaxed = true)
    private val crewMapper: CrewMapper = mockk(relaxed = true)
    private val sut = ArtistMapper(castMapper, crewMapper)

    @Test
    fun `Given a valid ArtistEntity When mapping is done Then a valid ArtistModel should be created`() {


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
        val cast = mockk<Cast>(relaxed = true){
            every { id } returns castEntity.id
            every { castId } returns castEntity.castId
            every { gender } returns castEntity.gender
            every { order } returns castEntity.order
            every { isAdult } returns castEntity.adult
            every { creditId } returns castEntity.creditId
            every { character } returns castEntity.character
            every { knownForDepartment } returns castEntity.knownForDepartment
            every { name } returns castEntity.name
            every { originalName } returns castEntity.originalName
            every { profilePath } returns castEntity.profilePath
            every { popularity } returns castEntity.popularity
        }

        every { castMapper.mapTo(castEntity) } returns cast

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
        val crew = mockk<Crew>(relaxed = true){
            every { id } returns crewEntity.id
            every { gender } returns crewEntity.gender
            every { isAdult } returns crewEntity.adult
            every { creditId } returns crewEntity.creditId
            every { department } returns crewEntity.department
            every { job } returns crewEntity.job
            every { knownForDepartment } returns crewEntity.knownForDepartment
            every { name } returns crewEntity.name
            every { originalName } returns crewEntity.originalName
            every { profilePath } returns crewEntity.profilePath
            every { popularity } returns crewEntity.popularity
        }
        every { crewMapper.mapTo(crewEntity) } returns  crew
        val artistEntity = ArtistEntity(
            id = 435,
            cast = listOf(castEntity),
            crew = listOf(crewEntity)
        )


        // When
        val artistModel = sut.mapTo(artistEntity)

        // Then
        assertEquals(artistEntity.id, artistModel.id)
        val castModel = artistModel.castList.first()

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

        val crewModel = artistModel.crewList.first()

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
