package com.fiannce.net.mode;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CommonBean implements CustomTabEntity {

    private String title;

    public CommonBean(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
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
