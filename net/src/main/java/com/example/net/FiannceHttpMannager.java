package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FiannceHttpMannager {
    private static FiannceApiModel apiModel;

    public static FiannceApiModel getApiModel() {
        if (apiModel == null) {
            apiModel = createApiModel();
        }
        return apiModel;
    }

    private static FiannceApiModel createApiModel() {
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(30000, TimeUnit.MINUTES)
                .readTimeout(30000, TimeUnit.MINUTES)
                .connectTimeout(30000, TimeUnit.MINUTES)
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(build)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://49.233.0.68:8080/")
                .build();

        return retrofit.create(FiannceApiModel.class);
    }
}
