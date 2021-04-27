package com.example.commom;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public static  String getString(Context context ,String key){
//        SpUtil.getString()
        SharedPreferences fiannceSp = context.getSharedPreferences("fiannceSp", Context.MODE_PRIVATE);
        return fiannceSp.getString(key,"");
    }

}
