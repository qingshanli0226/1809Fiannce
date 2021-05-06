package com.example.frame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class FrameArouter {
    public static FrameArouter frameArouter;

    public FrameArouter() {
    }

    public synchronized static FrameArouter getInstance() {
        if (frameArouter==null){
            frameArouter=new FrameArouter();
        }
        return frameArouter;
    }
    private IuserInterface iuserInterface;

    public void registerUserInterface(IuserInterface iuserInterface){
        this.iuserInterface=iuserInterface;
    }
    public IuserInterface getIuserInterface() {
        return iuserInterface;
    }


    public interface IuserInterface{
        void onGologin(Context context, Bundle bundle);
        void onGoregister(Context context,Bundle bundle);
    }
    private Map<String,Class<?>> map=new HashMap<>();
    private Class<?> displayActivity;
    private Context context;
    private Bundle bundle=null;
    private Intent intent=null;
    public void init(Context context){
        this.context=context;
    }
    public void registerpath(String path,Class<?> clazz){
        if (!map.containsKey(path)){
            map.put(path, clazz);
        }
    }
    public FrameArouter build(String path) {
        displayActivity = map.get(path);
        return this;
    }
    public FrameArouter with(Bundle bundle){
        this.bundle=bundle;
        return this;
    }
    public void navigation(){
         intent = new Intent(context, displayActivity);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public Intent getIntent() {
        return intent;
    }
}
