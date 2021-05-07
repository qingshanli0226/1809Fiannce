package com.example.financial.automatic

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.SPUtils
import com.example.financial.R
import com.example.financial.api.Api
import com.example.financial.base.AutoLogin
import com.example.financial.base.Request
import com.example.net_library.User
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream

// http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk
class LoginServer : Service() {
    private var notificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null

    inner class Bind : Binder() {
        fun getParent(): LoginServer {
            return this@LoginServer
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return Bind()
    }

    fun returnUpDataApk(url: String) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .download(url.substring(25))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<ResponseBody>() {
                override fun sure(t: ResponseBody) {
                    var byteStream = t.byteStream()
                    val contentLength = t.contentLength()
                    var path = "/sdcard/Download/app-debug.apk"
                    var file = File(path)
                    if (!file.exists()) {
                        file.createNewFile()
                    }

                    var fileOutputStream = FileOutputStream(file)
                    var byteArray = ByteArray(1024)
                    var read = 0
                    var progress = 0
                    while ((byteStream.read(byteArray).also { read = it }) != -1) {
                        fileOutputStream.write(byteArray, 0, read)
                        //Log.i("+++", "progress:$progress ")
                        //Log.i("+++", "contentLength:$contentLength ")
                        progress += read
                        initScheduleNotfity(contentLength.toInt(), progress)
                    }
                }
            })

    }

    fun returnAutomaticLogin() {
        if (SPUtils.getInstance().contains("token")) {
            RetrofitManger.retrofit.create(Api::class.java)
                .autoLogin(SPUtils.getInstance().getString("token"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<Request<AutoLogin>>() {
                    override fun sure(t: Request<AutoLogin>) {
                        if (t.result != null) {
                            User.long = true
                            User.token = t.result.token
                            SPUtils.getInstance().put("token", t.result.token)
                        }
                    }
                })
        }
    }

    fun initScheduleNotfity(max: Int, progress: Int) {
        if (notificationManager == null) {
            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            var notificationChannel =
                NotificationChannel("100", packageName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager!!.createNotificationChannel(notificationChannel)

            builder = NotificationCompat.Builder(this, "100")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("下载")
                .setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE)
                .setOnlyAlertOnce(true)
        }
        //Log.i("TAG", "initScheduleNotfity: " + builder)
        builder!!.setProgress(max, progress, false)
        if (max==progress) {
            builder!!.setContentTitle("下载完成")
            notificationManager!!.cancel(100)
        } else {
            notificationManager!!.notify(100, builder!!.build())
        }
    }


}