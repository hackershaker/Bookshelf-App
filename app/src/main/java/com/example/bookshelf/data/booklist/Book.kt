package com.example.bookshelf.data.booklist

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumnInfo: List<BookInfo>,
)