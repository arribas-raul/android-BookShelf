package com.arribas.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Book(
    val id: String,
    val kind: String,
    val etag: String,
    val selfLink: String,
    @SerialName(value = "volumeInfo")
    val volumeInfo: BookInfo?
)

@Serializable
data class BookInfo(
    val title: String,
    val subtitle: String = "",
    val authors: List<String> = listOf(""),
    val description: String = "",
    @SerialName(value = "imageLinks")
    val imageLinks: ImageLink = ImageLink(),
    val language: String
)

@Serializable
data class ImageLink(
    val smallThumbnail: String = "",
    val thumbnail: String = ""
)
