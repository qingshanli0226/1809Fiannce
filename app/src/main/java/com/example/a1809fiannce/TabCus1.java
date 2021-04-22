package com.example.a1809fiannce;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabCus1 implements CustomTabEntity {
    private String tit;

    public TabCus1(String tit) {
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
