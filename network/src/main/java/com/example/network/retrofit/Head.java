package com.example.network.retrofit;

import android.content.Context;

import com.example.common.ComContext;
import com.example.common.Squilts;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class Head implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (Squilts.getString(ComContext.getInstance().getContext())!=null){
            return chain.proceed(chain.request().newBuilder().addHeader("token", Squilts.getString(ComContext.getInstance().getContext())).build());
        }
        return chain.proceed(chain.request().newBuilder().addHeader("token","123").build());
    }
}
