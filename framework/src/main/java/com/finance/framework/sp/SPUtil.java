package com.finance.framework.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public static void putString(Context context,String key,String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,content);
        edit.commit();
    }

    public static boolean getBoolean(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP_NAME_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public static void putBoolean(Context context,String key,boolean content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,content);
        edit.commit();
    }
}
