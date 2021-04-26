package com.example.a1809zg.fragment;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CustomBean implements CustomTabEntity {
    private String name;
    private int a;
    private int b;

    public CustomBean(String name, int a, int b) {
        this.name = name;
        this.a = a;
        this.b = b;
    }

    @Override
    public String getTabTitle() {
        return name;
    }

    @Override
    public int getTabSelectedIcon() {
        return b;
    }

    @Override
    public int getTabUnselectedIcon() {
        return a;
    }
}
