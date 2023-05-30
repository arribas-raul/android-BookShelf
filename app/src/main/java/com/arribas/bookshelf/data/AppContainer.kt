package com.arribas.bookshelf.data

import com.arribas.bookshelf.network.BookService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookRepository: BookRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json{
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    override val bookRepository: BookRepository by lazy {
        DefaulBookRepository(retrofitService)
    }

}