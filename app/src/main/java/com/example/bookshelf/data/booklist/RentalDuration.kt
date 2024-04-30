package com.example.bookshelf.data.booklist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentalDuration(
    @SerialName("unit")
    val unit: String,
    @SerialName("count")
    val count: Int
)