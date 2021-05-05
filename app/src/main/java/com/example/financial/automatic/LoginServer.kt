package com.example.financial.automatic

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.SPUtils
import com.example.financial.api.Api
import com.example.financial.base.AutoLogin
import com.example.financial.base.Request
import com.example.net_library.R
import com.example.net_library.User
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.net.HttpURLConnection

class LoginServer : Service() {
    inner class Bind : Binder() {
        fun getParent(): LoginServer {
            return this@LoginServer
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return Bind()
    }

    fun download(path: String) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .download(path)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(object : BaseObserver<ResponseBody>() {
                override fun sure(t: ResponseBody) {

                }

                override fun onStart() {
                    super.onStart()
                    initNotfint()
                }
            })
    }

    private fun initNotfint() {
        var notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            100, Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_calendar_black_24dp)
                .setContentTitle("下载").build()
        )
    }

    fun starLogin() {
        if (SPUtils.getInstance().contains("token")) {
            RetrofitManger.retrofit.create(Api::class.java)
                .autoLogin(SPUtils.getInstance().getString("token"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<Request<AutoLogin>>() {
                    override fun sure(t: Request<AutoLogin>) {
                        User.long = true
                        User.token = t.result.token

                        SPUtils.getInstance().put("token", t.result.token)
                    }
                })
        }
    }
}