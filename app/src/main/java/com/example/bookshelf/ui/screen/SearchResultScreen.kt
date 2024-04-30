package com.example.bookshelf.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookshelf.R
import com.example.bookshelf.data.bookinfo.BookDetail
import com.example.bookshelf.util.CustomAsyncImage
import com.example.bookshelf.util.OnBottomReached

private val TAG = "SearchResultScreen"

@SuppressLint("RestrictedApi")
@Composable
fun SearchResultScreen(
    viewModel: LibraryViewModel,
    navController: NavController,
    onBackPressed: () -> Unit = {},
    bookOnClick: (Int) -> Unit = {},
    searchWord: String
) {
    Log.d(TAG, "검색 결과 스크린 Recomposition")
    val lazyListState = rememberLazyListState()
    val searchUiState by viewModel.searchUiState.collectAsState()
    val itemList = searchUiState.searchList

    BackHandler {
        Log.d(TAG, "검색 결과 페이지에서 BackHandler 실행")
        onBackPressed()
    }

    LazyColumn(modifier = Modifier, state = lazyListState) {
        items(count = itemList.size) {
            BookCard(book = itemList[it], onClick = bookOnClick, index = it)
        }
    }

    lazyListState.OnBottomReached {
        Log.d(TAG, "리스트 끝 도달")
        viewModel.getBooksList(searchWord)
        Log.d(TAG, "OnBottomReached 완료")
    }
}


@Composable
fun BookCard(
    book: BookDetail, modifier: Modifier = Modifier, onClick: (Int) -> Unit, index: Int
) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail
    val title = book.volumeInfo.title
    Row(
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxWidth()
            .aspectRatio(2.7f)
            .clickable { onClick(index) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomAsyncImage(
            model = imageUrl?.replace("http", "https"),
            contentDescription = book.volumeInfo.title,
            modifier = Modifier
                .background(Color.White)
                .aspectRatio(0.8f)
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
            placeholder = R.drawable.placeholder,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = title)
    }
}