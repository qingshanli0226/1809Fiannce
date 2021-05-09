package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCretor {

    private static FiannceApiService fiannceApiService;
    public static FiannceApiService getFiannceApiService() {
        if (fiannceApiService==null){
            fiannceApiService = createKSApiService();
        }
        return fiannceApiService;
    }

    private static FiannceApiService createKSApiService() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new TokenInterceptor());
        okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okBuilder.writeTimeout(2, TimeUnit.MINUTES);
        okBuilder.readTimeout(2,TimeUnit.MINUTES);
        okBuilder.callTimeout(2,TimeUnit.MINUTES);
        OkHttpClient build = okBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(build)
                .baseUrl("http://49.233.0.68:8080/").build();


        return retrofit.create(FiannceApiService.class);
    }
}
