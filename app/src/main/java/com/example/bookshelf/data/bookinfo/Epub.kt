package com.example.bookshelf.data.bookinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    @SerialName("isAvailable")
    val isAvailable: Boolean
)