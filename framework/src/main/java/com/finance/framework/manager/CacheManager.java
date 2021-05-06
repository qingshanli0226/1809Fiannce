package com.finance.framework.manager;

import com.finance.net.bean.HomeBean;
import com.finance.net.bean.UserBean;

public class CacheManager {
    private static CacheManager cacheManager;
    private HomeBean homeBean;

    public CacheManager() {
    }

    public static CacheManager getInstance() {
        if (cacheManager==null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

}
