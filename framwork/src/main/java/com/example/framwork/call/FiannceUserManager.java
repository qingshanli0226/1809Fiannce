package com.example.framwork.call;

import java.util.ArrayList;
import java.util.List;

public class FiannceUserManager {

    private List<IUserLoginChanged> list=new ArrayList<>();
    private static FiannceUserManager manager;

    public synchronized static FiannceUserManager getInstance() {
        if (manager==null){
            manager=new FiannceUserManager();
        }
        return manager;
    }
    public void Register(IUserLoginChanged iUserLoginChanged){
        list.add(iUserLoginChanged);
    }

    public void UnRegister(IUserLoginChanged iUserLoginChanged){
        list.remove(iUserLoginChanged);
    }
    
    private boolean isLog;

    public boolean getLog() {
        return isLog;
    }

    public void setLog(boolean isLog) {

        this.isLog = isLog;
        for (IUserLoginChanged listener : list) {
            listener.onLoginChange(isLog);
        }

    }

    public interface IUserLoginChanged{

        void onLoginChange(boolean isLog);

    }
}
