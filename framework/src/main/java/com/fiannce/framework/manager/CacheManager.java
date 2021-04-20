package com.fiannce.framework.manager;

import com.fiannce.net.mode.HomeBean;

public class CacheManager {

    private static CacheManager instance;
    private HomeBean homeBean;

    private CacheManager(){

    }
    public static synchronized CacheManager getInstance(){
        if (instance == null){
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
