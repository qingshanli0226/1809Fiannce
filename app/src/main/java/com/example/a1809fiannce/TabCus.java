package com.example.a1809fiannce;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabCus implements CustomTabEntity {
    private String tit;
    private int ICon;
    private int UnIcon;

    public TabCus(String tit, int ICon, int unIcon) {
        this.tit = tit;
        this.ICon = ICon;
        UnIcon = unIcon;
    }

    @Override
    public String getTabTitle() {
        return tit;
    }

    @Override
    public int getTabSelectedIcon() {
        return ICon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return UnIcon;
    }
}
