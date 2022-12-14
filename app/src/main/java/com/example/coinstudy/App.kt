package com.example.coinstudy

import android.app.Application
import android.content.Context
import timber.log.Timber

/**
 * Timber를 통해 로그 찍기 위한 클래스
 * */
class App : Application() {

    /**
     * datastore를 위한 로직
     * */
    init {
        instance = this
    }

    companion object {
        private var instance : App? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }


    /**
     * 로그 찍기 위한 로직
     * */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}