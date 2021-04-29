package com.example.framework.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class FrameArouter {
    private static FrameArouter frameArouter;

    private FrameArouter() {

    }
    public synchronized static FrameArouter getInstance(){
        if (frameArouter == null) {
            frameArouter = new FrameArouter();
        }
        return frameArouter;
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

    public FrameArouter build(String path){
        displayActivity = map.get(path);
        return this;
    }
    public FrameArouter with(Bundle bundle){
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
