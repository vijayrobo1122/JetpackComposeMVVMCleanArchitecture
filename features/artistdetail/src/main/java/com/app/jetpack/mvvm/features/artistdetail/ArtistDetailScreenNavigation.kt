package com.app.jetpack.mvvm.features.artistdetail

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources


fun NavController.navigateToArtistDetailScreen(artistId: String) {
    this.navigate(Screen.ArtistDetail.route.plus("/$artistId"))
}

fun NavGraphBuilder.artistDetailsScreen() {
    composable(
        Screen.ArtistDetail.route.plus(Screen.ArtistDetail.objectPath),
        arguments = listOf(navArgument(Screen.ArtistDetail.objectName) {
            type = NavType.IntType
        })
    ) {
        label = stringResource(StringResources.titleArtistDetail)
        val artistId = it.arguments?.getInt(Screen.ArtistDetail.objectName)
        artistId?.let { ArtistDetailScreen(artistId) }
    }
}
