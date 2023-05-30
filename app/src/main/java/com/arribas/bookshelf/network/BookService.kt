package com.arribas.bookshelf.network

import com.arribas.bookshelf.model.ResponseListBook
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") q:String): ResponseListBook
}