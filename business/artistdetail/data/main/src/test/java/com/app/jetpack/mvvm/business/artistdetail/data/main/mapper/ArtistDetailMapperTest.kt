package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistDetailEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistDetailMapperTest {
    private val sut = ArtistDetailMapper()

    @Test
    fun `Given a valid ArtistDetailEntity When mapping is done Then a valid ArtistDetail should be created`() {

        // Given
        val artistDetailEntity = ArtistDetailEntity(
            adult = true,
            alsoKnownAs = listOf("hello", "abcd"),
            biography = "biography",
            birthday = "birthday",
            gender = 1,
            homepage = "homepage",
            id = 123,
            imdbId = "imdbId",
            knownForDepartment = "knownForDepartment",
            name = "name",
            placeOfBirth = "placeOfBirth",
            popularity = 8.5,
            profilePath = "profilePath",
        )

        // When
        val artistDetailModel = sut.mapTo(artistDetailEntity)

        // Then
        assertEquals(artistDetailEntity.adult, artistDetailModel.isAdult)
        assertEquals(artistDetailEntity.alsoKnownAs, artistDetailModel.alsoKnownAsList)
        assertEquals(artistDetailEntity.biography, artistDetailModel.biography)
        assertEquals(artistDetailEntity.birthday, artistDetailModel.birthday)
        assertEquals(artistDetailEntity.gender, artistDetailModel.gender)
        assertEquals(artistDetailEntity.homepage, artistDetailModel.homepage)
        assertEquals(artistDetailEntity.id, artistDetailModel.id)
        assertEquals(artistDetailEntity.imdbId, artistDetailModel.imdbId)
        assertEquals(artistDetailEntity.knownForDepartment, artistDetailModel.knownForDepartment)
        assertEquals(artistDetailEntity.name, artistDetailModel.name)
        assertEquals(artistDetailEntity.placeOfBirth, artistDetailModel.placeOfBirth)
        assertEquals(artistDetailEntity.profilePath, artistDetailModel.profilePath)
    }
}
