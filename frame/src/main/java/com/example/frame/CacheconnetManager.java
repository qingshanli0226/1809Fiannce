package com.example.frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

public class CacheconnetManager {
    public static CacheconnetManager cacheconnetManager;

    public CacheconnetManager() {
    }

    public static CacheconnetManager getInstance() {
        if (cacheconnetManager==null){
            cacheconnetManager=new CacheconnetManager();
        }
        return cacheconnetManager;
    }

    private boolean isConnect;
    private Context context;
    private List<IConnect> list=new ArrayList<>();

    public boolean isConnect() {
        return isConnect;
    }
    public void init(Context context){
        this.context=context;
        getCurrentConnectStatus();
        initReceiver();
    }

    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver,intentFilter);
    }

    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            isConnect=true;
        }else {
            isConnect=false;
        }
    }
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                getCurrentConnectStatus();
                notifyConnectChange();
            }
        }
    };

        private void notifyConnectChange() {
            for (IConnect iConnect:list) {
                if (isConnect){
                    iConnect.onConnect();
                }else {
                    iConnect.onDisConnect();
                }
            }
        }
    public void registerConnectListener(IConnect connect){
        if (!list.contains(connect)){
            list.add(connect);
        }
    }
    public void unRegisterConnectListener(IConnect connect){
        if(list.contains(connect)){
            list.remove(connect);
        }
    }

    public interface IConnect{
        void onConnect();
        void onDisConnect();
    }
}
