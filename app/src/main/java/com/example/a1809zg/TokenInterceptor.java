package com.example.a1809zg;

import com.example.frame.SpUtil;
import com.example.net.NetMoudel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SpUtil.getString(NetMoudel.context, FiannceContants.Token_Key);
        Request request = chain.request();
        Request build = request.newBuilder().addHeader(FiannceContants.Token_Key, token).build();
        return chain.proceed(build);
    }
}
