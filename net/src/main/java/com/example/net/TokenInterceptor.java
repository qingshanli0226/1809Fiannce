package com.example.net;

import com.example.commom.FiannceConstants;
import com.example.commom.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String key = SpUtil.getString(NetModel.context, FiannceConstants.TOKEN_KEY);

        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader(FiannceConstants.TOKEN_KEY,key).build();
        return chain.proceed(newRequest);
    }
}
