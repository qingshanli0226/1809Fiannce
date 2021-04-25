package com.example.a1809fiannce.main.fragment.more.apk;

import android.content.Context;
import android.content.pm.PackageManager;

public class APKVersionCodeUtils {

    public static int getVersionCode(Context context){
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVerName(Context context){
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

}
