package com.example.kmpdamoproject

import android.app.Application
import com.example.kmpdamoproject.di.initKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}

