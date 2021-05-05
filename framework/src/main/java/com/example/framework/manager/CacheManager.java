package com.example.framework.manager;

import com.example.net.mode.HomeBean;

public class CacheManager {
    private CacheManager( ) {

    }

    private static CacheManager cacheManager;

    public synchronized static CacheManager getInstance() {
        if (cacheManager==null){
            cacheManager=new CacheManager();
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
