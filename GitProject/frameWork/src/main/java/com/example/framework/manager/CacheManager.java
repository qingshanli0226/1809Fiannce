package com.example.framework.manager;

import com.example.net.bean.HomeBean;

public class CacheManager {
    private static CacheManager cacheManager;

    private CacheManager() {
    }

    public static CacheManager getInstance(){
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    private HomeBean homeBean;




    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
