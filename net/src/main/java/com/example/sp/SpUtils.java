package com.example.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    public static String getString(Context context){
        SharedPreferences login = context.getSharedPreferences("login", 0);
        String token = login.getString("token", "");
        return token;
    }

    public static void putString(Context context,String token){
        SharedPreferences login = context.getSharedPreferences("login", 0);
        SharedPreferences.Editor edit = login.edit();
        edit.putString("token",token);
        edit.commit();
    }

}
