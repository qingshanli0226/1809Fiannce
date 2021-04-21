package com.fiannce.bawei.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    public static FiannceApiService fiannceApiService;

    public static FiannceApiService getfiannceApiService(){

        if (fiannceApiService == null){
            fiannceApiService = createKSApiService();
        }

        return fiannceApiService;
    }

    private static FiannceApiService createKSApiService(){

        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(60*1000, TimeUnit.MILLISECONDS)
                .readTimeout(60*1000, TimeUnit.MILLISECONDS)
                .writeTimeout(60*1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl("http://49.233.0.68:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(FiannceApiService.class);
    }



}
