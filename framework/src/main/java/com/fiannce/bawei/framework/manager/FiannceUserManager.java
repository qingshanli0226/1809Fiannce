package com.fiannce.bawei.framework.manager;

import com.fiannce.bawei.net.model.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {


    private List<IUserLoginChanged> list=new ArrayList<>();

    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (IUserLoginChanged iUserLoginChanged : list) {
            iUserLoginChanged.onLoginChanged(loginBean);
        }
    }

    public void registerUserLoginChanged(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }

    public void unregisterUserLoginChanged(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }

    public interface IUserLoginChanged{
        void onLoginChanged(LoginBean loginBean);
    }

    private static FiannceUserManager fiannceUserManager;

    public synchronized static FiannceUserManager getInstance() {
        if (fiannceUserManager==null){
            fiannceUserManager=new FiannceUserManager();
        }
        return fiannceUserManager;
    }

}
