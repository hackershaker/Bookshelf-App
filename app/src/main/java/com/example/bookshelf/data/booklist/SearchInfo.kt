package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchInfo(
    @SerialName("textSnippet")
    val textSnippet: String
)