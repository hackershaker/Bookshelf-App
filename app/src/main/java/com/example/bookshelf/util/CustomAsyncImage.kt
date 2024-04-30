package com.example.bookshelf.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.bookshelf.R

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    model: String?,
    contentDescription: String?,
    @DrawableRes placeholder: Int = R.drawable.placeholder,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
) {
    if (model != null) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale,
            alignment = alignment,
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(id = placeholder),
            contentDescription = contentDescription
        )
    }
}