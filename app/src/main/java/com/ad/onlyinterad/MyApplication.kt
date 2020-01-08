package com.ad.onlyinterad

import android.app.Application

/**
 * create by 2020/1/6
 *
 * author: wgl
 *
 * Believe in yourself, you can do it.
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AdClass.loadAd()
    }
}