package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public static String getString(Context context,String key){
        SharedPreferences autoToken = context.getSharedPreferences("autoToken", context.MODE_PRIVATE);
        return autoToken.getString(key,"");
    }

    public static void setString(Context context,String token){
        SharedPreferences autoToken = context.getSharedPreferences("autoToken", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = autoToken.edit();
        edit.putString(FiannceConstants.TOKEN_KEY,token);
        edit.commit();
    }
}
