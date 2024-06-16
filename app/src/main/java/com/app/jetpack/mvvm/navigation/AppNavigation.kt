package com.app.jetpack.mvvm.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Screen.Home.route}",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        composable(route = Screen.Popular.route) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Screen.Popular.route}",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        composable(route = Screen.TopRated.route) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Screen.TopRated.route}",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        composable(route = Screen.Upcoming.route) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Screen.Upcoming.route}",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        composable(route = Screen.Genre.route) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Screen.Genre.route} Details Screen",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
