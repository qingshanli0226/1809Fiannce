package com.fiannce.net;

import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.net.model.NetModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = SpUtil.getString(NetModel.context, CommonConstant.SP_TOKEN);
        Request newRequest = request.newBuilder().addHeader(CommonConstant.SP_TOKEN, token).build();
        return chain.proceed(newRequest);
    }
}
