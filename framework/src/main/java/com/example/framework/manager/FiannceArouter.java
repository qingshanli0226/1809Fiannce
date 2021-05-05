package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class FiannceArouter {


    public IUsetInterface iUsetInterface;
    public IAppInterface iAppInterface;


    public void registerUsetInterface(IUsetInterface iUsetInterface) {
        this.iUsetInterface = iUsetInterface;
    }

    public IUsetInterface getiUsetInterface() {
        return iUsetInterface;
    }

    public IAppInterface getiAppInterface() {
        return iAppInterface;
    }

    public void registerAppInterface(IAppInterface iAppInterface) {
        this.iAppInterface = iAppInterface;
    }

    public interface IUsetInterface {
        void openLoginActivity(Context context, Bundle bundle);

        void openReginActivity(Context context, Bundle bundle);
    }

    public interface IAppInterface {
        void openMainActivity(Context context, Bundle bundle);
    }


    private static FiannceArouter fiannceArouter;

    private FiannceArouter() {
    }

    public static synchronized FiannceArouter getInstance() {
        if (fiannceArouter == null) {
            fiannceArouter = new FiannceArouter();
        }
        return fiannceArouter;
    }


    public void init(Context context) {
        this.context = context;
    }

    private Context context;


    public HashMap<String, Class<?>> hashMap = new HashMap<>();
    private Class<?> displayActivityClass;

    public void registerActivityPath(String path, Class<?> clazz) {
        if (!hashMap.containsKey(path)) {
            hashMap.put(path, clazz);
        }
    }

    public FiannceArouter build(String path) {
        displayActivityClass = hashMap.get(path);
        return this;
    }

    public void navigation() {
        navigation(null);
    }

    public void navigation(Bundle bundle) {
        Intent intent = new Intent(context, displayActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (context instanceof Activity) {
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


}
