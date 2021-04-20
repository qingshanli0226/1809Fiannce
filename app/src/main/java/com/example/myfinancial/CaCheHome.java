package com.example.myfinancial;

import com.example.net.bean.HomeBean;

public class CaCheHome {
    private static HomeBean homeBean;

    public static HomeBean getHomeBean(HomeBean homeBean1){
        if (homeBean==null){
            homeBean=new HomeBean();
        }
        homeBean=homeBean1;
        return homeBean;
    }

}
