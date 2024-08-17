package com.example.a4photo1word.app

import android.app.Application
import com.example.a4photo1word.data.source.MySharedPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPref.init(this)
    }
}