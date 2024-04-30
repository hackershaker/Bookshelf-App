package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.bookshelf.ui.state.BookUiState

private val TAG = "bookshelf.LoadingScreen"

@Composable
fun LoadingScreen(
    uiState: State<BookUiState>,
    goToSearchResultScreen: () -> Unit,
    goToErrorScreen: () -> Unit,
) {
    Loading()

//    Log.d(TAG, "Loading Screen state 에서의 state: $uiState")
    when (uiState.value) {
        is BookUiState.Success -> {
            Log.d(TAG, "검색 결과 화면으로 이동.")
            goToSearchResultScreen()
        }

        else -> {}

//        is BookUiState.Loading -> Loading()
//
//        else -> {
//            Log.d(TAG, "에러 페이지로 이동")
//            goToErrorScreen()
//        }
    }
}

@Composable
fun Loading() {
    Text(text = "검색 결과를 기다리는 중입니다...")
}