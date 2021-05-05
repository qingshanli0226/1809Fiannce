package com.example.net;

import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String string = SpUtil.getString(NetModule.context, FianceConstants.TOKEN_KEY);
        return chain.proceed(chain.request().newBuilder().addHeader(FianceConstants.TOKEN_KEY,string).build());
    }
}
