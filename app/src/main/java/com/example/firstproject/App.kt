package com.example.firstproject

import android.app.Application
import android.content.Context

class App:Application() {
    companion object{
        private var instance:App? = null
        fun applicationContext():Context{
            return instance!!.applicationContext
        }
    }
    init{
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        //TODO call once when application created
    }
}