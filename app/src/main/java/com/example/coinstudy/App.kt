package com.example.coinstudy

import android.app.Application
import timber.log.Timber

/**
 * Timber를 통해 로그 찍기 위한 클래스
 * */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}