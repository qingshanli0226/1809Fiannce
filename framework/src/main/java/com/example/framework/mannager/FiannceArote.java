package com.example.framework.mannager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FiannceArote {
    private static FiannceArote arote;

    public FiannceArote() {
    }

    public static synchronized FiannceArote getInstance() {
        if (arote == null) {
            arote = new FiannceArote();
        }
        return arote;
    }

    private IUserInterface iUserInterface;
    private IPayInterface iPayInterface;
    private IAppInterface iAppInterface;
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    //注册user模块的回调借口
    public void registerIUserInterface(IUserInterface iUserInterface) {
        this.iUserInterface = iUserInterface;
    }
    //回去User模块的接口
    public IUserInterface getUserInterface() {
        return iUserInterface;
    }

    //注册支付页面回调接口
    public void registerIPayInterface(IPayInterface iPayInterface) {
        this.iPayInterface = iPayInterface;
    }
    public IPayInterface getPayInterface() {
        return iPayInterface;
    }

    public void registerIAppInterface(IAppInterface iAppInterface) {
        this.iAppInterface = iAppInterface;
    }
    public IAppInterface getAppInterface() {
        return iAppInterface;
    }


    ////在框架里约定好熬User要实现的接口，其他页面可以调用此接口
    public interface IUserInterface {
        void openLoginActivity(Context context, Bundle bundle);
        void openGettureActivity(Context context, Bundle bundle);
    }

    //定义支付模块实现接口
    public interface IPayInterface {
        void openPayActivity(Context context, Bundle bundle);
    }

    public interface IAppInterface {
        void openMainActivity(Context context, Bundle bundle);
        void onEvent(String event);
    }


}