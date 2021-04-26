package com.example.framework;

import com.example.net.bean.HomeBean;

public class CacheLoadMore {
    private static CacheLoadMore caCheHome;

    public CacheLoadMore() {
    }

    public static CacheLoadMore getInstance() {
        if (caCheHome == null) {
            caCheHome = new CacheLoadMore();
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
