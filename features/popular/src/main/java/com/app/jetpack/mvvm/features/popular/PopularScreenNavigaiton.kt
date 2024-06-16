package com.app.jetpack.mvvm.features.popular

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState

fun NavGraphBuilder.popularMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    composable(route = Screen.Popular.route) {
        PopularScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
            genresStateList = genresStateList
        )
    }
}
