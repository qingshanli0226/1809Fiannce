package com.finance.net.bean;

import java.io.Serializable;

public class UserBean {
    private String userName;
    private String passWord;
    private boolean isLogin;

    public UserBean(String userName, String passWord, boolean isLogin) {
        this.userName = userName;
        this.passWord = passWord;
        this.isLogin = isLogin;
    }

    public UserBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}
