package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class FinanceARouter {
    private static FinanceARouter financeARouter;
    private IUserManger iUserManger;
    private IAppManger iAppManger;

    public FinanceARouter() {
    }

    public static FinanceARouter getInstance(){
        if (financeARouter == null){
            financeARouter = new FinanceARouter();
        }
        return financeARouter;
    }

    public IUserManger getUserManger() {
        return iUserManger;
    }

    public void setUserManger(IUserManger iUserManger) {
        this.iUserManger = iUserManger;
    }

    public IAppManger getAppManger() {
        return iAppManger;
    }

    public void setAppManger(IAppManger iAppManger) {
        this.iAppManger = iAppManger;
    }

    public interface IUserManger{
        void openUserActivity(Context context,Bundle bundle);
    }

    public interface IAppManger{
        void openMainActivity(Context context,Bundle bundle);
        void openGesturePassword(Context context,Bundle bundle);
    }





}
