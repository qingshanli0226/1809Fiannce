package com.example.net_library.http

import android.media.session.MediaSession
import com.blankj.utilcode.util.SPUtils
import com.example.net_library.User
import okhttp3.Interceptor
import okhttp3.Response

class TokenHand : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (SPUtils.getInstance().contains("token")) {
            chain.proceed(chain.request().newBuilder().addHeader("token", SPUtils.getInstance().getString("token")).build())
        } else {
            chain.proceed(chain.request().newBuilder().build())
        }
    }
}