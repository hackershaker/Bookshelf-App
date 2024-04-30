package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    @SerialName("acsTokenLink")
    val acsTokenLink: String? = null,
    @SerialName("isAvailable")
    val isAvailable: Boolean
)