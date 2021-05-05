package com.example.framework.manager;

import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

public class CacheManager {

    public VersionBean versionBean;

    public VersionBean getVersionBean() {
        return versionBean;
    }

    public void setVersionBean(VersionBean versionBean) {
        this.versionBean = versionBean;
    }

    public HoemBean hoemBean ;

    public HoemBean getHoemBean() {
        return hoemBean;
    }

    public void setHoemBean(HoemBean hoemBean) {
        this.hoemBean = hoemBean;
    }





    private static CacheManager simpleManager;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (simpleManager == null) {
            simpleManager = new CacheManager();
        }
        return simpleManager;
    }

}
