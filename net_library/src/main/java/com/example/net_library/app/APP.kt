package com.example.net_library.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.net_library.evenuilt.ContextInstrument


class APP:Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)
        ContextInstrument.context=this
    }
}