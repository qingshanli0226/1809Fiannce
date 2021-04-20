package com.example.formwork;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager manager;

    public static RetrofitManager getManager() {
        if (manager==null){
            manager=new RetrofitManager();
        }
        return manager;
    }
    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        if (retrofit==null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            Retrofit build = new Retrofit.Builder()
                    .baseUrl("http://49.233.0.68:8080/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofit=build;
        }
        return retrofit;
    }
}
