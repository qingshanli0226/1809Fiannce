package com.example.network.retrofit;

import com.example.common.FiannceNetConfig;
import com.example.network.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Api api;

    public static Api getApi() {
        if (api==null){
            api=getRetrofit();
        }
        return api;
    }

    public static Api getRetrofit() {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            Retrofit build = new Retrofit.Builder()
                    .baseUrl(FiannceNetConfig.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

       return build.create(Api.class);
    }
}
