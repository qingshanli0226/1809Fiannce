package com.fiannce.bawei.framework.manager;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {

    private static FiannceUserManager instance;
    //集合储存拥有登录状态的所有页面
    private List<IUserLoginChanged> iUserLoginChangeds = new ArrayList<>();




    public static synchronized FiannceUserManager getInstance(){
        if (instance == null){
            instance = new FiannceUserManager();
        }
        return instance;
    }
    //是否登录的状态
    private boolean isLogin;
    public boolean isLogin() {
        return isLogin;
    }
    public void setLogin(boolean login) {
       isLogin = login;
       for (IUserLoginChanged iUserLoginChanged:iUserLoginChangeds){
           iUserLoginChanged.onLoginChange(login);
       }
    }

    public void register(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangeds.add(iUserLoginChanged);
    }
    public void unRegister(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangeds.remove(iUserLoginChanged);
    }
    public interface IUserLoginChanged{
        void onLoginChange(boolean isLogin);
    }
}
