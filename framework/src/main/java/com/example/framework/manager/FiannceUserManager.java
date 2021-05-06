package com.example.framework.manager;

import com.example.net.mode.LogBean;

import java.util.LinkedList;
import java.util.List;

public class FiannceUserManager {

    private List<IUserLoginChanged> iUserLoginChangedList = new LinkedList<>();

    private static FiannceUserManager instance;

    private FiannceUserManager(){};

    public static synchronized FiannceUserManager getInstance(){
        if (instance == null){
            instance = new FiannceUserManager();
        }
        return instance;
    }

    private boolean isLogin;

    public boolean isLogin(){
        return isLogin;
    }

    public void register(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangedList.add(iUserLoginChanged);
    }

    public void unRegister(IUserLoginChanged iUserLoginChanged){
        iUserLoginChangedList.remove(iUserLoginChanged);
    }

    private LogBean isLog;

    public LogBean getLog(){
        return isLog;
    }

    public void setLogin(LogBean isLog){
        this.isLogin = isLogin;

    }


    public interface IUserLoginChanged{
        void onLoginChange(LogBean isLogin);
    }
}
