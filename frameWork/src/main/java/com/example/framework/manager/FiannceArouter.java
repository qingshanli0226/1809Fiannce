package com.example.framework.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class FiannceArouter {
    private static FiannceArouter fiannceArouter;

    public FiannceArouter() {
    }

    public static FiannceArouter getInstance(){
        if (fiannceArouter == null){
            fiannceArouter = new FiannceArouter();
        }
        return fiannceArouter;
    }

    //第二章 arouter
    private Map<String,Class<?>> map = new HashMap<>();
    private Class<?> displayActivity;
    private Context context;
    private Bundle bundle = null;
    private Intent intent = null;
    public void init(Context context){
        this.context = context;
    }
    public void registerPath(String path,Class<?> clazz){
        if (!map.containsKey(path)) {
            map.put(path,clazz);
        }
    }

    public FiannceArouter build(String path){
        displayActivity = map.get(path);
        return this;
    }
    public FiannceArouter with(Bundle bundle){
        this.bundle = bundle;
        return this;
    }
    public void navigation(){
        intent = new Intent(context, displayActivity);
        if(context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        } else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public Intent getIntent() {
        return intent;
    }

    public Bundle getBundle() {
        return bundle;
    }

    //第一种跳转 接口回调
    private IUserInterface iUserInterface;


    public void registerUserInterface(IUserInterface iUserInterface){
        this.iUserInterface = iUserInterface;
    }

    public IUserInterface getUserInterface(){
        return iUserInterface;
    }

    //User接口
    public interface IUserInterface{
        void onGoLogin(Context context, Bundle bundle);
        void onGoRegister(Context context,Bundle bundle);
    }

}
