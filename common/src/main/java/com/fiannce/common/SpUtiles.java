package com.fiannce.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtiles {

    public static String getString(Context context) {
        SharedPreferences login = context.getSharedPreferences("autologin.txt", Context.MODE_PRIVATE);
        String token = login.getString("token", "");
        return token;
    }

    public static void putString(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("autologin.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("token", token);
        edit.commit();
    }

}
