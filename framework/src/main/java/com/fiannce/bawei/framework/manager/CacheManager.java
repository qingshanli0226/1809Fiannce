package com.fiannce.bawei.framework.manager;

import com.fiannce.bawei.net.mode.HomeBean;

import okhttp3.Cache;

public class CacheManager {

    private static CacheManager instance;
    private HomeBean homeBean;


    private CacheManager() {

    }

    //多线程去实例化该实例
    public static synchronized CacheManager getInstance() {
        if (instance==null){
            instance = new CacheManager();
        }
        return instance;
    }


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
