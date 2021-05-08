package com.fiannce.bawei.framework;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.commom.FiannceContants;

public class SpUtil {


    public static String getString(Context context,String key){

        SharedPreferences wang = context.getSharedPreferences("wang", Context.MODE_PRIVATE);

        return wang.getString(key,"");
    }

    public static void setString(Context context,String key){
        SharedPreferences wang = context.getSharedPreferences("wang", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = wang.edit();

        edit.putString(FiannceContants.TOKEN_KEY,key);

        edit.commit();


    }

}
