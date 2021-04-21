package com.example.net_library.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter


class APP:Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)
    }
}