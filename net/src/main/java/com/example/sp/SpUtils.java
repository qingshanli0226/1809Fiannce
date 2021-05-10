package com.example.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    public static String getString(Context context){
        SharedPreferences login = context.getSharedPreferences("login", 0);
        String token = login.getString("token", "");
        return token;
    }

    public static void putString(Context context,String token){
        SharedPreferences login = context.getSharedPreferences("login", 0);
        SharedPreferences.Editor edit = login.edit();
        edit.putString("token",token);
        edit.commit();
    }

    public static Boolean getGestureBoolean(Context context){
        SharedPreferences login = context.getSharedPreferences("gesture", 0);
        Boolean gestureboolean = login.getBoolean("gestureboolean", false);
        return gestureboolean;
    }

    public static void putGestureBoolean(Context context,Boolean gestureboolean){
        SharedPreferences login = context.getSharedPreferences("gesture", 0);
        SharedPreferences.Editor edit = login.edit();
        edit.putBoolean("gestureboolean",gestureboolean);
        edit.commit();
    }

    public static String getGesturePwdResult(Context context){
        SharedPreferences login = context.getSharedPreferences("gestureresult", 0);
        String gestureresult = login.getString("gestureresult", "");
        return gestureresult;
    }

    public static void putGesturePwdResule(Context context,String result){
        SharedPreferences login = context.getSharedPreferences("gestureresult", 0);
        SharedPreferences.Editor edit = login.edit();
        edit.putString("gestureresult",result);
        edit.commit();
    }

}
