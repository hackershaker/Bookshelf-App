package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("finskyOfferType")
    val finskyOfferType: Int,
    @SerialName("listPrice")
    val listPrice: ListPriceX,
    @SerialName("retailPrice")
    val retailPrice: RetailPrice,
    @SerialName("rentalDuration")
    val rentalDuration: RentalDuration? = null
)