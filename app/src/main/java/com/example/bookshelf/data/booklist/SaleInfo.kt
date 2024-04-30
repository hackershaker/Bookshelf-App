package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    @SerialName("buyLink")
    val buyLink: String? = null,
    @SerialName("country")
    val country: String,
    @SerialName("isEbook")
    val isEbook: Boolean,
    @SerialName("listPrice")
    val listPrice: ListPrice? = null,
    @SerialName("offers")
    val offers: List<Offer>? = null,
    @SerialName("retailPrice")
    val retailPrice: RetailPriceX? = null,
    @SerialName("saleability")
    val saleability: String
)