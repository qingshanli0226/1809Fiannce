package com.example.net;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.net.module.NetModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = SpUtil.getString(NetModule.context, CommonConstant.SP_TOKEN);
        Request newRequest = request.newBuilder().addHeader(CommonConstant.SP_TOKEN,token).build();
        return chain.proceed(newRequest);
    }
}
