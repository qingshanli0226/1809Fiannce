package com.finance.net;

import android.provider.ContactsContract;
import android.provider.SyncStateContract;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        chain.proceed(chain.request().newBuilder().addHeader("", "token").build());
        return null;
    }
}
