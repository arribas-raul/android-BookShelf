package com.arribas.bookshelf

import android.app.Application
import com.arribas.bookshelf.data.AppContainer
import com.arribas.bookshelf.data.DefaultAppContainer

class AppApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
    }
}