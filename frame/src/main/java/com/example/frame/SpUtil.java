package com.example.frame;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences fiannce = context.getSharedPreferences("fiannce", Context.MODE_PRIVATE);
        return fiannce.getString(key,"");
    }
    public static void putString(Context context,String key,String content){
        SharedPreferences fiannce = context.getSharedPreferences("fiannce", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = fiannce.edit();
        edit.putString(key,content);
        edit.commit();

    }
}
