package com.example.frame;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences fiannce = context.getSharedPreferences("fiannce", Context.MODE_PRIVATE);
        return fiannce.getString(key,"");
    }
}
