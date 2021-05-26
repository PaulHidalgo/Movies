package com.mojix.movie.util.common

import android.app.Application

/**
 *Created by PHIDALGO on 2020
 */
class BaseApp : Application() {
    companion object{
        lateinit var instance: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}