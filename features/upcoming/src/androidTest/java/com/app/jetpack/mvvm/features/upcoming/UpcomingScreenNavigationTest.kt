package com.app.jetpack.mvvm.features.upcoming

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UpcomingScreenNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testUpcomingScreenDisplaysGenres() {
        val genres = arrayListOf(
            GenreState("All"),
            GenreState("Action"),
            GenreState("Adventure"),
            GenreState("Animation"),
            GenreState("Comedy"),
            GenreState("Crime"),
        )

        composeTestRule.setContent {
            UpcomingScreen(
                isShowExitAppDialog = false,
                onMovieItemClick = {},
                genresStateList = genres
            )
        }

        genres.forEach { genre ->
            composeTestRule.onNodeWithText(genre.name).assertExists()
        }
    }

    @Test
    fun testUpcomingScreenOnMovieItemClick() {
        var clickedMovieId: String? = null
        val onMovieItemClick: (String) -> Unit = { movieId ->
            clickedMovieId = movieId
        }

        composeTestRule.setContent {
            UpcomingScreen(
                isShowExitAppDialog = false,
                onMovieItemClick = onMovieItemClick,
                genresStateList = arrayListOf()
            )
        }

        // Assuming there is a movie item with text "Movie 1" to click
        composeTestRule.onNodeWithText("Movie 1").performClick()

        assert(clickedMovieId == "1022789")
    }
}

