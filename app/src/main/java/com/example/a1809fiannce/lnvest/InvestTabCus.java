package com.example.a1809fiannce.lnvest;

import com.flyco.tablayout.listener.CustomTabEntity;

public class InvestTabCus implements CustomTabEntity {
    private String tit;

    public InvestTabCus(String tit) {
        this.tit = tit;

    }

    @Override
    public String getTabTitle() {
        return tit;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
