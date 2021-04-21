package com.example.frame;

import com.example.net.bean.HomeBean;

public class CacheMore {
    private static CacheMore cacheMore;

    public CacheMore() {
    }

    public static CacheMore getInstance() {
        if (cacheMore==null){
            cacheMore=new CacheMore();
        }
        return cacheMore;
    }
    private HomeBean homeBean;

    public synchronized HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
