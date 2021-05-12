package com.fiannce.framework.manager;

import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;

public class CacheManager {
    private static CacheManager instance;
    private HomeBean homeBean;
    public boolean gesture = false;

    private CacheManager() {

    }

    public VersionBean versionBean;

    public VersionBean getVersionBean() {
        return versionBean;
    }

    public void setVersionBean(VersionBean versionBean) {
        this.versionBean = versionBean;
    }


    //多线程去实例化该实例
    public static synchronized CacheManager getInstance() {
        if (instance == null) {
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
