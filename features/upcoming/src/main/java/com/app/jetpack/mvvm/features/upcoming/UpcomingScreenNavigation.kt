package com.app.jetpack.mvvm.features.upcoming

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.upcomingMovieScreen(
    onMovieItemClick: (String) -> Unit,
) {
    composable(route = Screen.Upcoming.route) {
        UpcomingScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
        )
    }
}
