package com.example.commom;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.net.NetModule;

public class SpUtil {

    public static  String getString(Context context ,String key){
        SpUtil.getString(NetModule.context,FianceConstants.TOKEN_KEY);
        SharedPreferences fiannceSp = context.getSharedPreferences("fiannceSp", Context.MODE_PRIVATE);
        return fiannceSp.getString(key,"");
    }

}
