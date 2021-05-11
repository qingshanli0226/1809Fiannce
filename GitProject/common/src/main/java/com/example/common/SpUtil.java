package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String  getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonConstant.SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
    public static boolean  getBoolean(String name,Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }
    public static String  getString(String name,Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
    public static void putString(Context context,String key,String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonConstant.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,content);
        edit.commit();
    }

    public static void putBoolean(String name,Context context,String key,boolean content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,content);
        edit.commit();
    }
    public static void putString(String name,Context context,String key,String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,content);
        edit.commit();
    }

}
