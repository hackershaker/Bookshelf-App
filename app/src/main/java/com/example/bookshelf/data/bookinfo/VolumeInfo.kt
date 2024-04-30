package com.example.bookshelf.data.bookinfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("allowAnonLogging")
    val allowAnonLogging: Boolean,
    @SerialName("authors")
    val authors: List<String>? = null,
    @SerialName("canonicalVolumeLink")
    val canonicalVolumeLink: String,
    @SerialName("contentVersion")
    val contentVersion: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier>,
    @SerialName("infoLink")
    val infoLink: String,
    @SerialName("language")
    val language: String,
    @SerialName("maturityRating")
    val maturityRating: String,
    @SerialName("pageCount")
    val pageCount: Int? = null,
    @SerialName("panelizationSummary")
    val panelizationSummary: PanelizationSummary? = null,
    @SerialName("previewLink")
    val previewLink: String,
    @SerialName("printType")
    val printType: String,
    @SerialName("printedPageCount")
    val printedPageCount: Int? = null,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("readingModes")
    val readingModes: ReadingModes,
    @SerialName("subtitle")
    val subtitle: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null
)