package com.example.framework;

import com.example.net.bean.HomeBean;

public class CaCheLoadMore {
    private static CaCheLoadMore caCheHome;

    public CaCheLoadMore() {
    }

    public static CaCheLoadMore getInstance() {
        if (caCheHome == null) {
            caCheHome = new CaCheLoadMore();
        }
        return caCheHome;
    }
    private HomeBean homeBean;
    public synchronized HomeBean getHomeBean(){
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
