package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.bookshelf.ui.state.BookUiState

private val TAG = "ErrorScreen"

@Composable
fun ErrorScreen(viewModel: LibraryViewModel) {
    val state = viewModel.bookUiState.collectAsState()
    Log.d(TAG, "ErrorScreen state : $state")
    Column {
        Text(text = "오류가 발생하였습니다.")
        Text(text = (state.value as BookUiState.Error).e.toString())
    }
}