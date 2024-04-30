package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RetailPriceX(
    @SerialName("amount")
    val amount: Int,
    @SerialName("currencyCode")
    val currencyCode: String
)