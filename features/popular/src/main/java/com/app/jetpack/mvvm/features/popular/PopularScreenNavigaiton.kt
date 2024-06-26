package com.app.jetpack.mvvm.features.popular

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.popularMovieScreen(
    onMovieItemClick: (String) -> Unit,
) {
    composable(route = Screen.Popular.route) {
        PopularScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
        )
    }
}
