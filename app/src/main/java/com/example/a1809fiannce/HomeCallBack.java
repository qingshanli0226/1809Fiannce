package com.example.a1809fiannce;

public class HomeCallBack {
    private static HomeCallBack homeCallBack;

    public static HomeCallBack getHomeCallBack() {
        if (homeCallBack==null){
            homeCallBack=new HomeCallBack();
        }
        return homeCallBack;
    }

    
}
