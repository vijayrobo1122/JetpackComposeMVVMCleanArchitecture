package com.app.jetpack.mvvm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.jetpack.mvvm.R
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.resources.drawables.DrawableResources

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    navController: NavController,
    genres: List<String>,
    closeDrawer: (genreName: String) -> Unit
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(items = genres, itemContent = { menuItem ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = menuItem,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 18.sp
                        )
                    },
                    onClick = {
                        navController.navigate(Screen.Genre.route)
                        closeDrawer(menuItem)
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp),
                            imageVector = Icons.Default.Movie,
                            contentDescription = menuItem,
                        )
                    },
                    shape = MaterialTheme.shapes.small,
                    selected = false,
                )
            })
        }
    }
}


@Composable
private fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(15.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = DrawableResources.icBaselineAccountCircle),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
