package com.example.a1809fiannce.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyCustomTabEntity implements CustomTabEntity {
    private String name;
    private int a;
    private int b;

    public MyCustomTabEntity(String name, int a, int b) {
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