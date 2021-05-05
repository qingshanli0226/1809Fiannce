package com.example.commom;

import android.content.Context;
import android.content.SharedPreferences;


public class SpUtil {
    public static  String getString(Context context ,String key){
        SharedPreferences fiannceSp = context.getSharedPreferences(FianceConstants.SP_FIANNCE, Context.MODE_PRIVATE);
        return fiannceSp.getString(key,"");
    }


    public static  void setString(Context context ,String key,String mag){
        SharedPreferences.Editor editor = context.getSharedPreferences(FianceConstants.SP_FIANNCE, Context.MODE_PRIVATE).edit();
        editor.putString(key,mag);
        editor.commit();
    }
}
