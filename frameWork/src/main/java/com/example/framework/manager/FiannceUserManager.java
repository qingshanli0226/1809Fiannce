package com.example.framework.manager;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {

    private List<IUserLoginChanged> loginChangedList = new ArrayList<>();

    private static FiannceUserManager instance;
    private FiannceUserManager(){}

    public static synchronized FiannceUserManager getInstance(){
        if (instance==null){
            instance=new FiannceUserManager();
        }
        return instance;
    }

    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }


    public void register(IUserLoginChanged iUserLoginChanged){
        loginChangedList.add(iUserLoginChanged);
    }


    public void unRegister(IUserLoginChanged iUserLoginChanged){
        loginChangedList.remove(iUserLoginChanged);
    }

    public void setLogin(boolean isLogin){
        this.isLogin = isLogin;
        for (IUserLoginChanged iUserLoginChanged : loginChangedList) {
            iUserLoginChanged.onLoginChange(isLogin);
        }
    }

    public interface IUserLoginChanged{
        void onLoginChange(boolean isLogin);
    }

}
