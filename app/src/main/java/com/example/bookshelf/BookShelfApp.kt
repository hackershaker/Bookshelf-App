package com.example.bookshelf

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.ui.screen.BookDetailScreen
import com.example.bookshelf.ui.screen.ErrorScreen
import com.example.bookshelf.ui.screen.LibraryViewModel
import com.example.bookshelf.ui.screen.LoadingScreen
import com.example.bookshelf.ui.screen.SearchResultScreen
import com.example.bookshelf.ui.screen.StartScreen
import kotlinx.coroutines.launch

private const val TAG = "BookShelfApp"

enum class BookShelfScreen {
    Start, Loading, SearchResult, BookDetail, Error
}

@Composable
fun BookShelfApp(viewModel: LibraryViewModel) {
    val uiState = viewModel.bookUiState.collectAsState()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val text: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    SnackbarHost(hostState = snackbarHostState)
    val searchOnCilck: (String?) -> Unit = { textString ->
        if (textString.isNullOrEmpty()) {
            // TODO: snackbar로 입력이 비었다고 알리기.
//            coroutineScope.launch{}
            coroutineScope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()
                Log.d(TAG, "snackbar triggered")
                snackbarHostState.showSnackbar(
                    message = "검색어를 입력해 주세요.",
                    actionLabel = "닫기",
                    duration = SnackbarDuration.Short,
                )
            }
        } else {
            coroutineScope.launch {
                viewModel.getBooksList(text.value)
            }
            navController.navigate(
                BookShelfScreen.Loading.name
            )
        }
    }


    NavHost(navController = navController, startDestination = BookShelfScreen.Start.name) {
        composable(BookShelfScreen.Start.name) {
            StartScreen(
                searchOnClick = { searchOnCilck(text.value) },
                text = text,
                keyboardSearchOnClick = { searchOnCilck(text.value) },
                snackbarHostState = snackbarHostState,
            )
        }
        composable(BookShelfScreen.Loading.name) {
            LoadingScreen(uiState = uiState,
                goToSearchResultScreen = { navController.navigate(BookShelfScreen.SearchResult.name) },
                goToErrorScreen = {
                    navController.navigate(
                        BookShelfScreen.Error.name
                    )
                })
        }
        composable(BookShelfScreen.Error.name) { ErrorScreen(viewModel = viewModel) }
        composable(BookShelfScreen.SearchResult.name) {
            SearchResultScreen(
//                uiState = uiState,
                navController = navController,
                onBackPressed = {
                    navController.navigate(BookShelfScreen.Start.name) {
                        popUpTo(BookShelfScreen.Start.name) { inclusive = true }
                    }
//                    viewModel.updateState(BookUiState.Start())
                }, viewModel = viewModel, bookOnClick = { idx ->
                    viewModel.searchUiState.value.searchStartIndex = idx
                    navController.navigate(route = BookShelfScreen.BookDetail.name)
                }, searchWord = text.value
            )
        }
        composable(BookShelfScreen.BookDetail.name) {
            BookDetailScreen(
                viewModel = viewModel,
            )
        }
    }
}

