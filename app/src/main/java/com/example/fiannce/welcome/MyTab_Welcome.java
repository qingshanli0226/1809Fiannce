package com.example.fiannce.welcome;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTab_Welcome implements CustomTabEntity {

    private String title;
    private int SelectColor;
    private int unSelectColor;

    public MyTab_Welcome(String title, int selectColor, int unSelectColor) {
        this.title = title;
        SelectColor = selectColor;
        this.unSelectColor = unSelectColor;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return SelectColor;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectColor;
    }
}
