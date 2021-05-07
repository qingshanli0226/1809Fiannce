package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import java.util.LinkedList;
import java.util.List;

public class CacheConnectManager {

    //单例
    public static CacheConnectManager cacheConnectManager;
    private CacheConnectManager() {
    }

    public static CacheConnectManager getInstance() {
        if (cacheConnectManager == null) {
            cacheConnectManager = new CacheConnectManager();
        }
        return cacheConnectManager;
    }



    private boolean isConnect;
    private Context context;
    private List<IConnect> list = new LinkedList<>();

    public boolean isConnect() {
        return isConnect;
    }
    public void init(Context applicationContext){
        this.context = applicationContext;
        getCurrentConnectStatus();
        initReceiver();
    }
    //获得链接状态
    public synchronized void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            isConnect = true;
        } else{
            isConnect = false;
        }
    }
    //注册广播
    public void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //网络发生改变自动发送广播
            if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStatus();
                notifyConnectChange();
            }
        }
    };

    //判断状态
    public synchronized void notifyConnectChange(){
        for (IConnect iConnect : list) {
            if(isConnect){
                iConnect.onConnect();
            } else{
                iConnect.onDisConnect();
            }
        }
    }

    //注册接口
    public synchronized void registerConnectListener(IConnect connect){
        if (!list.contains(connect)) {
            list.add(connect);
        }
    }
    //取消注册接口
    public synchronized void unRegisterConnectListener(IConnect connect){
        if (list.contains(connect)) {
            list.remove(connect);
        }
    }


    //链接状态返回
    public interface IConnect{
        void onConnect();
        void onDisConnect();
    }


}
