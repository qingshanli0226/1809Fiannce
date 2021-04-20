package com.example.myfinancial;

import com.example.net.bean.HomeBean;

public class CaCheHome {
    private static CaCheHome caCheHome;

    public CaCheHome() {
    }

    public static CaCheHome getInstance() {
        if (caCheHome == null) {
            caCheHome = new CaCheHome();
        }
        return caCheHome;
    }
    private HomeBean homeBean;
    public synchronized HomeBean getHomeBean(){
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
