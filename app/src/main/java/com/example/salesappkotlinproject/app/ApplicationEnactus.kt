package com.example.salesappkotlinproject.app

import android.app.Application
import com.example.salesappkotlinproject.data.local.AppDatabase
import com.example.salesappkotlinproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class ApplicationEnactus : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ApplicationEnactus)
            inject()
        }
    }

    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(
            listOf(
                fragmentModule,
                viewModelModule,
                repositoryModule,
                databaseModule,
                networkRepository
            )
        )
    }
}