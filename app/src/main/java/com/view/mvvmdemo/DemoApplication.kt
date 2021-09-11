package com.view.mvvmdemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.view.mvvmdemo.di.modules

class DemoApplication : Application() {

    companion object {
        private var instance: Application? = null
        private var appToken: String = "-1"
        fun getInstance() = instance!!
        fun getappToken() = appToken
        fun setappToken(token: String) {
            appToken = token
        }
    }


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApplication)
            androidLogger()
            modules(modules)
            instance = this@DemoApplication


        }
    }


}