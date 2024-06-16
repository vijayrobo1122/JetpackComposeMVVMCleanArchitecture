package com.app.jetpack.mvvm.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.app.jetpack.mvvm.R
import com.app.jetpack.mvvm.common.domain.DataState
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.navigation.currentRoute
import com.app.jetpack.mvvm.common.navigation.navigationTitle
import com.app.jetpack.mvvm.common.ui.compositions.AppBarWithArrow
import com.app.jetpack.mvvm.common.ui.compositions.HomeAppBar
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
import com.app.jetpack.mvvm.navigation.AppNavigation
import com.app.jetpack.mvvm.ui.components.AppDrawer
import com.app.jetpack.mvvm.ui.components.BottomNavigationUI
import com.app.jetpack.mvvm.utils.ConnectionState
import com.app.jetpack.mvvm.utils.connectivityState
import kotlinx.coroutines.launch

const val DEFAULT_GENRE_ITEM = "All"

@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val genreName = remember { mutableStateOf("") }
    val genreList = remember { mutableStateOf(arrayListOf<GenreState>()) }

    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // genre api call for first time
    LaunchedEffect(key1 = 0) {
        mainViewModel.genreList()
    }

    if (mainViewModel.genreStateList.value is DataState.Success<List<GenreState>>) {
        genreList.value =
            (mainViewModel.genreStateList.value as DataState.Success<ArrayList<GenreState>>).data

        // All first value as all
        if (genreList.value.first().name != DEFAULT_GENRE_ITEM) {
            genreList.value.add(0, GenreState(genreId = null, name = DEFAULT_GENRE_ITEM))
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                modifier = Modifier,
                navController = navController,
                genres = genreList.value,
            ) {
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
                snackbarHost = {
                    if (isConnected.not()) {
                        SnackbarHost(snackbarHostState, snackbar = {
                            Snackbar(
                                action = {}, modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = stringResource(StringResources.thereIsNoInternet))
                            }
                        })
                    }
                },
            ) { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        genresStateList = genreList.value,
                    )
                }
            }
        })
}
