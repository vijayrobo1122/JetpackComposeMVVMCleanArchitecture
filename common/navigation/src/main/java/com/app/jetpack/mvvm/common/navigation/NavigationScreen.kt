package com.app.jetpack.mvvm.common.navigation


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources

sealed class Screen(
    val route: String,
    @StringRes val title: Int = StringResources.appTitle,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Genre : Screen("genre_screen")
    object Home : Screen("home_screen")
    object Popular : Screen("popular_screen")
    object TopRated : Screen("top_rated_screen")
    object Upcoming : Screen("upcoming_screen")
    object NavigationDrawer :
        Screen("navigation_drawer", objectName = "genreId", objectPath = "/{genreId}")

    object MovieDetail :
        Screen("movie_detail_screen", objectName = "movieItem", objectPath = "/{movieItem}")

    object ArtistDetail :
        Screen("artist_detail_screen", objectName = "artistId", objectPath = "/{artistId}")

    // Bottom Navigation
    object HomeNav : Screen("home_screen", title = StringResources.tabHome, navIcon = {
        Icon(
            Icons.Filled.Home,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object PopularNav : Screen("popular_screen", title = StringResources.tabPopular, navIcon = {
        Icon(
            Icons.Filled.Timeline,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object TopRatedNav : Screen("top_rated_screen", title = StringResources.tabTopRate, navIcon = {
        Icon(
            Icons.Filled.Star,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object UpcomingNav : Screen("upcoming_screen", title = StringResources.tabUpComing, navIcon = {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })
}
