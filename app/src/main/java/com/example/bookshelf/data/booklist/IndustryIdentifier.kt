package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifier(
    @SerialName("identifier")
    val identifier: String,
    @SerialName("type")
    val type: String
)