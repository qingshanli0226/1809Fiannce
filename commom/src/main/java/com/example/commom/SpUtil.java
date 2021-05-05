package com.example.commom;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("fiannceSp", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void setString(Context context,String key){
        SharedPreferences fiannceSp = context.getSharedPreferences("fiannceSp", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = fiannceSp.edit();

        edit.putString(FiannceConstants.TOKEN_KEY,key);

        edit.commit();
    }
}
