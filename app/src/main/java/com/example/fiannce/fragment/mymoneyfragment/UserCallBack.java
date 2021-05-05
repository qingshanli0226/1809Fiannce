package com.example.fiannce.fragment.mymoneyfragment;

public class UserCallBack {
    private static UserCallBack callBack;

    public static UserCallBack getInstance(){
        if (callBack == null){
            callBack = new UserCallBack();
        }
        return callBack;
    }

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
