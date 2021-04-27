package com.example.net_library.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManger {
    private val time = 60L
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(InterUrl.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .callFactory(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(time, TimeUnit.SECONDS)
                .readTimeout(time, TimeUnit.SECONDS)
                .connectTimeout(time, TimeUnit.SECONDS)
                .build())
            .build()
    }
}