package com.example.framework.manager;

import com.example.model.HomeBean;
import com.example.model.ProductBean;

public class CacheManager {

    private static CacheManager instance;
    private HomeBean homeBean;


    public CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance==null){
            instance = new CacheManager();
        }
        return instance;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }


}
