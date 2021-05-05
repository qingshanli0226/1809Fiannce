package com.example.financial.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.financial.ui.MainActivity
import com.example.net_library.communication.ModuleCommunication
import com.example.user_library.login.LoginActivity


class APP:Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)

        ModuleCommunication.init(this)

        ModuleCommunication.registeredActivity(ModuleCommunication.STAR_ACTIVITY_LOGIN,
            LoginActivity::class.java)

        ModuleCommunication.registeredActivity(ModuleCommunication.STAR_ACTIVITY_MAIN,
            MainActivity::class.java)

    }
}