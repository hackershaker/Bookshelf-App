package com.example.bookshelf.data.booklist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("allowAnonLogging")
    val allowAnonLogging: Boolean,
    @SerialName("authors")
    val authors: List<String>? = null,
    @SerialName("averageRating")
    val averageRating: Float? = null,
    @SerialName("canonicalVolumeLink")
    val canonicalVolumeLink: String,
    @SerialName("categories")
    val categories: List<String>? = null,
    @SerialName("contentVersion")
    val contentVersion: String,
    @SerialName("comicsContent")
    val comicsContent: Boolean? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null,
    @SerialName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier>? = null,
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
    @SerialName("publishedDate")
    val publishedDate: String? = null,
    @SerialName("publisher")
    val publisher: String? = null,
    @SerialName("readingModes")
    val readingModes: ReadingModes,
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subtitle: String? = null
)