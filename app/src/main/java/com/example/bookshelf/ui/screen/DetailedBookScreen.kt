package com.example.bookshelf.ui.screen

import HtmlText
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookshelf.ui.state.BookUiState
import com.example.bookshelf.util.CustomAsyncImage

private const val TAG = "DetailedBookScreen"

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier, viewModel: LibraryViewModel,
) {
    val index = viewModel.searchUiState.collectAsState().value.searchStartIndex
    val uiState = viewModel.bookUiState.collectAsState()
    val bookInfo = (uiState.value as BookUiState.Success).bookList[index]
    val imageUrl = bookInfo.volumeInfo.imageLinks?.thumbnail
    val title = bookInfo.volumeInfo.title
    val author = bookInfo.volumeInfo.authors
    val description = bookInfo.volumeInfo.description
    val scrollState = rememberScrollState()

    Log.d(TAG, "$bookInfo")

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp, 32.dp, 8.dp, 32.dp)
                .border(1.dp, Color.White),
        ) {
            CustomAsyncImage(
                model = imageUrl?.replace("http", "https"),
                contentDescription = null,
                modifier = Modifier.height(200.dp),
            )
            Spacer(
                modifier = Modifier
                    .padding(12.dp)
                    .border(2.dp, color = Color.Gray)
            )
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = title)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = author?.joinToString() ?: "")
            }
        }

        HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(10.dp))
        Spacer(modifier = modifier.padding(top = 32.dp))

        // 확장 animation 구현
        Text(text = "책 정보")
        Text(text = HtmlText(htmlString = description ?: "책 정보가 없습니다."))

    }
}