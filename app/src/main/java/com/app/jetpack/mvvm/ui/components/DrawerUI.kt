package com.app.jetpack.mvvm.ui.components


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DrawerUI(
    navController: NavController,
    genres: List<String>,
    closeDrawer: (genreName: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(items = genres, itemContent = { item ->
            DrawerItem(
                item = item,
                selected = false,
                onItemClick = {
                    // Close drawer
                    closeDrawer(it)
                })
        })
    }
}
