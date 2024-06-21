package com.app.jetpack.mvvm.features.upcoming

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState

fun NavGraphBuilder.upcomingMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    composable(route = Screen.Upcoming.route) {
        UpcomingScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
            genresStateList = genresStateList,
        )
    }
}
