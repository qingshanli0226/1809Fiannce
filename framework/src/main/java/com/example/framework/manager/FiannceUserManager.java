package com.example.framework.manager;

import android.util.Log;

import com.example.net.model.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {


    private List<IUserLoginChanged> iUserLoginChangedList = new ArrayList<>();

    private LoginBean loginBean;

    public interface IUserLoginChanged {
        void onLoginChange(LoginBean loginBean);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (IUserLoginChanged iUserLoginChanged : iUserLoginChangedList) {
            iUserLoginChanged.onLoginChange(loginBean);
        }
    }

    public void register(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangedList.add(iUserLoginChanged);
    }



    private static FiannceUserManager fiannceUserManager;

    public static FiannceUserManager getInstance() {
        if (fiannceUserManager == null) {
            fiannceUserManager = new FiannceUserManager();
        }
        return fiannceUserManager;
    }
}
