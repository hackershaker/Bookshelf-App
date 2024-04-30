package com.example.bookshelf.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

@Composable
fun LazyListState.OnBottomReached(
    loadMore: suspend () -> Unit
) {
    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {

            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                // list is empty
                // return false here if loadMore should not be invoked if the list is empty
                return@derivedStateOf true

            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
//         layoutinfo 값을 사용해서 side-effect를 진행할 대는 snapshotFlow를 권장.
        snapshotFlow { shouldLoadMore.value }.collect {
            if (it) loadMore()
        }
    }
}