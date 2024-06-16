package com.app.jetpack.mvvm.features.upcoming

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.domain.model.moviedetail.Genre
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.upcomingMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genres: ArrayList<Genre>? = null,
) {
    composable(route = Screen.Upcoming.route) {
        UpcomingScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
            genres = genres,
        )
    }
}
