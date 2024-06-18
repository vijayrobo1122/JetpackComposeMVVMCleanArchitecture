package com.app.jetpack.mvvm.common.general.extensions

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}

@Composable
fun <T : Any> LazyPagingItems<T>.pagingLoadingState(
    isLoaded: (pagingState: Boolean) -> Unit,
) {
    this.apply {
        when {
            // data is loading for first time
            loadState.refresh is LoadState.Loading -> {
                isLoaded(true)
            }
            // data is loading for second time or pagination
            loadState.append is LoadState.Loading -> {
                isLoaded(true)
            }

            loadState.refresh is LoadState.NotLoading -> {
                isLoaded(false)
            }

            loadState.append is LoadState.NotLoading -> {
                isLoaded(false)
            }
        }
    }
}

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}
