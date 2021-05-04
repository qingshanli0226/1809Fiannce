package com.example.a1809fiannce.mian;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MainTabCus implements CustomTabEntity {
    private String tit;
    private int Icon;
    private int UnIcon;

    public MainTabCus(String tit, int icon, int unIcon) {
        this.tit = tit;
        Icon = icon;
        UnIcon = unIcon;
    }

    @Override
    public String getTabTitle() {
        return tit;
    }

    @Override
    public int getTabSelectedIcon() {
        return Icon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return UnIcon;
    }
}
