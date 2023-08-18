package com.example.koindemo

import android.app.Application
import android.content.Context
import com.example.koindemo.di.module.appModule
import com.example.koindemo.di.module.repoModule
import com.example.koindemo.di.module.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance=this
        startKoin {
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

    companion object {
        lateinit var mInstance: App
        fun getmInstance(): App? {
            return mInstance
        }

        fun setmInstance(mInstance: App) {
            Companion.mInstance = mInstance
        }

    }

}