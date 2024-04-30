package com.example.bookshelf.data.booklist

import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(val title: String, val authors: List<String>, val imageLinks: List<String>)