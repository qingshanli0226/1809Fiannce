package com.example.a1809fiannce.tab;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyCustomTabEntity implements CustomTabEntity {

    private String title;
    private int tabSelectedIcon;
    private int tabUnselectedIcon;

    public MyCustomTabEntity(String title, int tabSelectedIcon, int tabUnselectedIcon) {
        this.title = title;
        this.tabSelectedIcon = tabSelectedIcon;
        this.tabUnselectedIcon = tabUnselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabUnselectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabSelectedIcon;
    }
}
