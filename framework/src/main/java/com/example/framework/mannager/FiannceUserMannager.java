package com.example.framework.mannager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiannceUserMannager {
    //维护一个接口
    private List<IUserLoginChanged> iUserLoginChangedList = new LinkedList<>();

    private static FiannceUserMannager mannager;

    public static FiannceUserMannager getInstance() {
        if (mannager == null) {
            mannager = new FiannceUserMannager();
        }
        return mannager;
    }

    private boolean isLogin;//登陆状态

    //获取当前登陆状态
    public boolean isLogin() {
        return isLogin;
    }

    public void register(IUserLoginChanged iUserLoginChanged) {
        if (!iUserLoginChangedList.contains(iUserLoginChanged)) {
            iUserLoginChangedList.add(iUserLoginChanged);
        }
    }

    public void unRegister(IUserLoginChanged iUserLoginChanged) {
        if (iUserLoginChangedList.contains(iUserLoginChanged)) {
            iUserLoginChangedList.remove(iUserLoginChanged);
        }
    }

    //当登录成功或者退出登录时   状态发生改变
    public void setIsLwogin(boolean isLogin) {
        this.isLogin = isLogin;
        //通知各个页面当前的登录状态
        for (IUserLoginChanged changed : iUserLoginChangedList) {
            changed.onLoginChanged(isLogin);
        }
    }


    public interface IUserLoginChanged {
        void onLoginChanged(boolean isLogin);
    }
}
