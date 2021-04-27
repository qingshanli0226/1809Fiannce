package com.example.net;

import android.content.Context;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.net.model.NetConstant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = SpUtil.getString(NetConstant.context, CommonConstant.SP_TOKEN);
        Request newRequest = request.newBuilder().addHeader("head","token").build();
        return chain.proceed(newRequest);
    }
}
