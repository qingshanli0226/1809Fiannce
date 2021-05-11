package com.fiannce.commond;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonConstant.SP_TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void putString(Context context, String key, String content) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonConstant.SP_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, content);
        edit.commit();
    }

    public static boolean getUpdateApk(Context context, String key) {
        SharedPreferences fiannceSp = context.getSharedPreferences(CommonConstant.SP_FIANNCE, Context.MODE_PRIVATE);
        return fiannceSp.getBoolean(key, false);
    }


    public static void setUpdateApk(Context context, String key, boolean b) {
        SharedPreferences.Editor edit = context.getSharedPreferences(CommonConstant.SP_FIANNCE, Context.MODE_PRIVATE).edit();
        edit.putBoolean(key, b);
        edit.commit();
    }
}
