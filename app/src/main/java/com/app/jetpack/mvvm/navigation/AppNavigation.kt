package com.app.jetpack.mvvm.navigation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen

@Composable
fun AppNavigation(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(navController, startDestination = Screen.Home.route, modifier) {
        composable(route = Screen.Home.route) {
            Column {
                Text(
                    text = "${Screen.Home.route}", modifier = Modifier.fillMaxSize(),
                    fontSize = 42.sp
                )
            }
        }
        composable(route = Screen.Popular.route) {
            Column {
                Text(
                    text = "${Screen.Popular.route}", modifier = Modifier.fillMaxSize(),
                    fontSize = 42.sp
                )
            }
        }
        composable(route = Screen.TopRated.route) {
            Column(

            ) {
                Text(
                    text = "${Screen.TopRated.route}", modifier = Modifier.fillMaxSize(),
                    fontSize = 42.sp
                )
            }
        }
        composable(route = Screen.Upcoming.route) {
            Column {
                Text(
                    text = "${Screen.Upcoming.route}", modifier = Modifier.fillMaxSize(),
                    fontSize = 42.sp
                )
            }
        }
    }
}
