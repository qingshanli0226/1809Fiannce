package com.example.fiannce.adapter;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTab implements CustomTabEntity {

    private String title;
    private int SelectColor;
    private int unSelectColor;

    public MyTab(String title, int selectColor, int unSelectColor) {
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
