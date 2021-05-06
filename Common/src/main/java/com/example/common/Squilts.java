package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Squilts {

    private static Squilts squilts;

    public static Squilts getInstance() {
        if (squilts==null){
            squilts=new Squilts();
        }
        return squilts;
    }

    public static String getString(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("auto", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;

    }

    public static void putString(Context context,String token){

        SharedPreferences sharedPreferences = context.getSharedPreferences("auto", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("token",token);
        edit.commit();

    }
}
