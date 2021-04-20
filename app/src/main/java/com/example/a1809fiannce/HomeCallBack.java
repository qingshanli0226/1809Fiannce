package com.example.a1809fiannce;

import com.example.formwork.model.HomeBean;

public class HomeCallBack {
    private static HomeCallBack homeCallBack;
    private HomeBean homeBean;
    public static HomeCallBack getHomeCallBack() {
        if (homeCallBack==null){
            homeCallBack=new HomeCallBack();
        }
        return homeCallBack;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
