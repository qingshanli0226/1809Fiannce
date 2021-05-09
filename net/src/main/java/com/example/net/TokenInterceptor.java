package com.example.net;

import android.view.animation.Interpolator;

import com.example.sp.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = SpUtils.getString(NetModel.context);
        Request build = request.newBuilder().addHeader("token", token).build();
        return chain.proceed(build);
    }
}
