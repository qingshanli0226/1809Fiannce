package com.example.gitproject.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CustomBean implements CustomTabEntity {
    private String title;
    private int selectIcon;
    private int unSelectIcon;

    public CustomBean() {
    }

    public CustomBean(String title, int selectIcon, int unSelectIcon) {
        this.title = title;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectIcon;
    }
}
