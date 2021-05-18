package com.example.framework.manager;

import java.util.ArrayList;
import java.util.List;

public class FinanceUserManager {

    private List<IUserLoginChanged> loginChangedList = new ArrayList<>();

    private static FinanceUserManager instance;
    private FinanceUserManager(){}

    public static synchronized FinanceUserManager getInstance(){
        if (instance==null){
            instance=new FinanceUserManager();
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
