package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshelf.R
import com.example.compose.BookShelfTheme

private const val TAG = "StartScreen"

// 메인 화면, 검색 창
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    searchOnClick: () -> Unit,
    text: MutableState<String>,
    keyboardSearchOnClick: () -> Unit,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    density: Density = LocalDensity.current,
) {
    val imeInsets = WindowInsets.ime
    val scrollState = rememberScrollState()

    Log.d(
        TAG, "키보드 크기\n" +
                "top: ${imeInsets.getTop(density)}\n" +
                "bottom ${imeInsets.getBottom(density)}\n" +
                "left: ${imeInsets.getLeft(density, LayoutDirection.Ltr)}\n" +
                "right: ${imeInsets.getRight(density, LayoutDirection.Ltr)}\n"
    )


    Scaffold(
        modifier = modifier
            .imePadding(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier,
            )
        },
    ) { padding ->
//        ime padding을 column에 적용하려면 scrollable 해야 한다.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .imePadding()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.main_screen_title), fontSize = 48.sp)
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.book_image),
                contentDescription = stringResource(R.string.main_screen_image_description),
            )
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text(text = stringResource(R.string.main_screen_searchbar_label)) },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        keyboardSearchOnClick()
                    }),
                )
                Button(
                    modifier = Modifier,
                    onClick = { searchOnClick() }) {
                    Text(text = stringResource(R.string.main_screen_search_button_text))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    BookShelfTheme {
    }
}