package com.app.jetpack.mvvm.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources

@Composable
fun NavController.navigationTitle(): String {
    return when (this.currentRoute()) {
        Screen.MovieDetail.route -> stringResource(StringResources.titleMovieDetail)
        Screen.ArtistDetail.route -> stringResource(StringResources.titleArtistDetail)
        else -> {
            ""
        }
    }
}

@Composable
fun NavController.currentRoute(): String? {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}

@Composable
fun NavController.isShowExitAppDialog(): Boolean {
    return (this.currentRoute() == Screen.Home.route)
}
