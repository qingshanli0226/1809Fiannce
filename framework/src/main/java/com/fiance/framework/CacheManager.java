package com.fiance.framework;

import com.fiance.net.mode.HomeBean;

public class CacheManager {
    private static com.fiance.framework.CacheManager instance;
    private HomeBean homeBean;


    private CacheManager(){

    }

    public static com.fiance.framework.CacheManager getInstance() {

        if (instance==null){
            instance=new com.fiance.framework.CacheManager();
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
