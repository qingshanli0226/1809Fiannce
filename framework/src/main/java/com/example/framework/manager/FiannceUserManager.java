package com.example.framework.manager;

import com.example.net.mode.LogBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiannceUserManager {

    private List<IUserLoginChanged> list = new ArrayList<>();

    private static FiannceUserManager manager;

    public synchronized static FiannceUserManager getInstance(){
        if (manager == null){
            manager = new FiannceUserManager();
        }
        return manager;
    }

    public void register(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }

    public void unRegister(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }

    private LogBean isLog;

    public LogBean getIsLog(){
        return isLog;
    }

    public void setIsLog(LogBean isLog){
        this.isLog = isLog;

    }


    public interface IUserLoginChanged{
        void onLoginChange(LogBean islog);
    }
}
