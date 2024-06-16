package com.app.jetpack.mvvm.features.genredetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.genreDetailScreen(
    onMovieItemClick: (String) -> Unit,
) {
    composable(
        route = Screen.NavigationDrawer.route.plus(Screen.NavigationDrawer.objectPath),
        arguments = listOf(navArgument(Screen.NavigationDrawer.objectName) {
            type = NavType.StringType
        })
    ) { backStack ->
        val genreId = backStack.arguments?.getString(Screen.NavigationDrawer.objectName)
        genreId?.let {
            GenreDetailScreen(
                isShowExitAppDialog = false,
                onMovieItemClick = onMovieItemClick,
                genreId = genreId
            )
        }
    }
}
