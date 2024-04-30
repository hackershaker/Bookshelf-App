package com.example.bookshelf.data.bookinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetail(
    @SerialName("accessInfo")
    val accessInfo: AccessInfo,
    @SerialName("etag")
    val etag: String,
    @SerialName("id")
    val id: String,
    @SerialName("kind")
    val kind: String,
    @SerialName("saleInfo")
    val saleInfo: SaleInfo,
    @SerialName("selfLink")
    val selfLink: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)