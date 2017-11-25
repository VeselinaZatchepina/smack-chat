package com.github.veselinazatchepina.smack.controller

import android.app.Application
import com.github.veselinazatchepina.smack.utils.SharedPrefs


class App: Application() {

    companion object {
        lateinit var sharedPrefs: SharedPrefs
    }

    override fun onCreate() {
        sharedPrefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}