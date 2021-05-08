package com.fiannce.bawei.framework.manager;

import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.LoginBean;

public class CacheManager {

    private  static CacheManager instance;

    private HomeBean homeBean;

    public CacheManager() {
    }

    public static  synchronized  CacheManager getInstance(){

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

    private Libean libean;

    public Libean getLibean() {
        return libean;
    }

    public void setLibean(Libean libean) {
        this.libean = libean;
    }

    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}