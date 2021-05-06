package com.example.frame;

import com.example.net.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

public class CacheUserManager {
    private static CacheUserManager cacheUserManager;

    public CacheUserManager() {
    }

    public synchronized static CacheUserManager getInstance() {
        if (cacheUserManager==null){
            cacheUserManager=new CacheUserManager();
        }
        return cacheUserManager;
    }
    private LoginBean loginBean;
    private List<ILoginChange> iLoginChangeList=new ArrayList<>();
    public void registerLogin(ILoginChange iLoginChange){
        iLoginChangeList.add(iLoginChange);
    }
    public void unregisterLogin(ILoginChange iLoginChange){
        iLoginChangeList.remove(iLoginChange);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        for (ILoginChange iLoginChange:iLoginChangeList
             ) {
            iLoginChange.onLoginChange(loginBean);

        }
    }


    public interface ILoginChange{
        void onLoginChange(LoginBean loginBean);
    }
}
