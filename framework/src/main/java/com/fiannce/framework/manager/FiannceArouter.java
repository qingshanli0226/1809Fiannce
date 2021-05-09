package com.fiannce.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class FiannceArouter {

    public Class<?> displayActivityClass;
    private Context context;

    private HashMap<String, Class<?>> pathMap = new HashMap<>();

    private IUserInterface iUserInterface;
    private IPayInterface iPayInterface;
    private IAppInterface iAppInterface;

    private static FiannceArouter instance;

    private FiannceArouter() {

    }

    public void init(Context context) {
        this.context = context;
    }

    public static synchronized FiannceArouter getInstance() {
        if (instance == null) {
            instance = new FiannceArouter();
        }
        return instance;
    }


    public void registerIUserInterface(IUserInterface iUserInterface) {
        this.iUserInterface = iUserInterface;
    }

    public IUserInterface getiUserInterface() {
        return iUserInterface;
    }

    public void registerIPayInterface(IPayInterface iPayInterface) {
        this.iPayInterface = iPayInterface;
    }

    public IPayInterface getiPayInterface() {
        return iPayInterface;
    }

    public void registerIAppInterface(IAppInterface iAppInterface) {
        this.iAppInterface = iAppInterface;
    }

    public IAppInterface getiAppInterface() {
        return iAppInterface;
    }

    public interface IUserInterface {
        void openLoginActivity(Context context, Bundle bundle);

        void openGetureActivity(Context context, Bundle bundle);
    }

    public interface IPayInterface {
        void openPayActivity(Context context, Bundle bundle);
    }

    public interface IAppInterface {
        void openMainActivity(Context context, Bundle bundle);

        void onEvent(String event);
    }

    public void registerActivityPath(String path, Class<?> clazz) {
        if (!pathMap.containsKey(path)) {
            pathMap.put(path, clazz);
        }
    }

    public FiannceArouter build(String path) {
        displayActivityClass = pathMap.get(path);
        return this;
    }

    public void navigation() {
        Intent intent = new Intent(context, displayActivityClass);
        if (context instanceof Activity) {
            context.startActivity(intent);
        }
        {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
