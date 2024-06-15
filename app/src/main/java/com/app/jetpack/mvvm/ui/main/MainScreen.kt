package com.app.jetpack.mvvm.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.app.jetpack.mvvm.R
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.navigation.currentRoute
import com.app.jetpack.mvvm.common.navigation.navigationTitle
import com.app.jetpack.mvvm.common.ui.compositions.AppBarWithArrow
import com.app.jetpack.mvvm.common.ui.compositions.HomeAppBar
import com.app.jetpack.mvvm.navigation.AppNavigation
import com.app.jetpack.mvvm.ui.components.BottomNavigationUI
import com.app.jetpack.mvvm.ui.components.DrawerUI
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val genreName = remember { mutableStateOf("All") }
    val genreList = remember {
        mutableStateOf(
            listOf(
                "All",
                "Horor",
                "Action",
                "Family",
                "Animation",
                "Fancy"
            )
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerUI(navController, genreList.value as List<String>) {
                genreName.value = it
                scope.launch {
                    drawerState.close()
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    when (navController.currentRoute()) {
                        Screen.Home.route, Screen.Popular.route, Screen.TopRated.route, Screen.Upcoming.route, Screen.NavigationDrawer.route -> {
                            val appTitle: String =
                                if (navController.currentRoute() == Screen.NavigationDrawer.route) genreName.value
                                else stringResource(R.string.app_title)
                            HomeAppBar(title = appTitle, openDrawer = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            })
                        }

                        else -> {
                            AppBarWithArrow(navController.navigationTitle()) {
                                navController.popBackStack()
                            }
                        }
                    }
                },
                bottomBar = {
                    when (navController.currentRoute()) {
                        Screen.Home.route, Screen.Popular.route, Screen.TopRated.route, Screen.Upcoming.route -> {
                            BottomNavigationUI(navController)
                        }
                    }
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppNavigation(navController, Modifier.padding(innerPadding))
                }
            }
        })

}
