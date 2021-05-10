package com.example.net;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static FiannceApiService fiannceApiService;

    public static FiannceApiService getFiannceApiService() {
        if (fiannceApiService == null) {
            fiannceApiService =createKSApiService();
        }
        return fiannceApiService;
    }

    private static FiannceApiService createKSApiService() {

        FiannceApiService fiannceApiService = new Retrofit.Builder()
                .client(
                        new OkHttpClient.Builder()
                                .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                                .addInterceptor(new TokenInterceptor())
                                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .build()
                )
                .baseUrl(AppNetConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FiannceApiService.class);

        return fiannceApiService;

    }


}
