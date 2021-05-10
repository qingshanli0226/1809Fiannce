package com.example.framwork.call;

import android.content.Context;
import android.os.Bundle;

public class FiannceARouter {
    private iUserManager userManager;
    private iAppManager appManager;

    public FiannceARouter() {
    }
    private static FiannceARouter fiannceARouter;

    public static FiannceARouter getFiannceARouter() {
        if (fiannceARouter==null){
            fiannceARouter=new FiannceARouter();
        }
        return fiannceARouter;
    }

    public iUserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(iUserManager userManager) {
        this.userManager = userManager;
    }

    public iAppManager getAppManager() {
        return appManager;
    }

    public void setAppManager(iAppManager appManager) {
        this.appManager = appManager;
    }

    public interface iUserManager{
        void OpenUserActivity(Context context,Bundle bundle);
    }

    public interface iAppManager{
        void OpenMainActivity(Context context,Bundle bundle);
    }


}
