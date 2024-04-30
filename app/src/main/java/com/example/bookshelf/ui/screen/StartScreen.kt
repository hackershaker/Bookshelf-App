package com.example.bookshelf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.compose.BookShelfTheme

private val cropShape =
    GenericShape { size: Size, layoutDirection: androidx.compose.ui.unit.LayoutDirection ->
        addRect(Rect(1f, 1f, 1f, 1f))
    }

private val TAG = "StartScreen"

// 메인 화면, 검색 창
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    searchOnClick: () -> Unit,
    text: MutableState<String>,
    keyboardSearchOnClick: () -> Unit,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    val snackbarModifier = Modifier

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.main_screen_title))
            Image(
                painter = painterResource(id = R.drawable.book_image),
                contentDescription = stringResource(R.string.main_screen_image_description),
                modifier = Modifier,
            )
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = stringResource(R.string.main_screen_searchbar_label)) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardSearchOnClick()
                }),
            )
            Button(onClick = { searchOnClick() }) {
                Text(text = stringResource(R.string.main_screen_search_button_text))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    val text = rememberSaveable { mutableStateOf("") }
    val viewModel = viewModel<LibraryViewModel>(factory = LibraryViewModel.Factory)
    BookShelfTheme {
    }
}