package com.app.jetpack.mvvm.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.app.jetpack.mvvm.common.navigation.Screen

@Composable
fun BottomNavigationUI(navController: NavController) {

    var navigationSelectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        val items = listOf(
            Screen.HomeNav,
            Screen.PopularNav,
            Screen.TopRatedNav,
            Screen.UpcomingNav,
        )
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                label = {
                    Text(text = stringResource(id = navigationItem.title))
                },
                icon = {
                    navigationItem.navIcon
                },
                selected = index == navigationSelectedItem,
                onClick = {
                    navigationSelectedItem = index

                    navController.navigate(navigationItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        /*  navController.graph.startDestinationRoute?.let { route ->
                              popUpTo(route) {
                                  saveState = true
                              }
                          }*/
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}
