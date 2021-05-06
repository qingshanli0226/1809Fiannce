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
}
