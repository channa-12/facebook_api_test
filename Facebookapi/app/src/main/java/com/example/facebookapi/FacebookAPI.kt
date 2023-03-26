package com.example.facebookapi

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class FacebookAPI: MultiDexApplication(){
    companion object {
        lateinit var instance: FacebookAPI
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}