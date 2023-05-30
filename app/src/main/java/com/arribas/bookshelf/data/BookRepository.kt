package com.arribas.bookshelf.data

import com.arribas.bookshelf.model.ResponseListBook
import com.arribas.bookshelf.network.BookService

interface BookRepository {
    suspend fun getBooks(q: String): ResponseListBook
}

class DefaulBookRepository(
    private val bookApiService: BookService
): BookRepository {

    override suspend fun getBooks(q: String): ResponseListBook {
        return bookApiService.getBooks(q= q)
    }

}