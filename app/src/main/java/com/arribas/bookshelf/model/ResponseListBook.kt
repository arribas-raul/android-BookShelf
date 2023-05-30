package com.arribas.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseListBook(
    val kind:String,
    val totalItems: Int,
    val items: List<Book>
)