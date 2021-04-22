package com.example.fiannce;

import com.example.net.mode.HomeBean;

public class HomeCallBack {

    private static HomeCallBack homeCallBack;
    private HomeBean homeBean;

    public static HomeCallBack getHomeCallback(){
        if (homeCallBack == null){
            homeCallBack = new HomeCallBack();
        }
        return homeCallBack;
    }

    public HomeBean getHomeBean(){
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean){
        this.homeBean = homeBean;
    }
}
