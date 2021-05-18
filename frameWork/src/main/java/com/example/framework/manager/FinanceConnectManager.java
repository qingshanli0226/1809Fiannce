package com.example.framework.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class FinanceConnectManager {

    private boolean isConnected;
    private Context context;

    private static FinanceConnectManager instance;
    private List<IConnectListener> connectListeners = new LinkedList<>();

    public FinanceConnectManager(){

    }

    public static FinanceConnectManager getInstance() {
        if (instance==null){
            instance = new FinanceConnectManager();
        }
        return instance;
    }

    public void init(Context applicationContext){
        this.context = applicationContext;
        getCurrentConnectStatus();
        initReceiver();
    }

    private void getCurrentConnectStatus(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork!=null && activeNetwork.isConnected()){
            isConnected = true;
        }else {
            isConnected = false;
        }
    }

    private void initReceiver(){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netConnectReceiver,intentFilter);
    }

    private BroadcastReceiver netConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getCurrentConnectStatus();
                notifyConnectChanged();
            }
        }
    };

    private void notifyConnectChanged(){
        for (IConnectListener connectListener : connectListeners) {
            if (isConnected){
                connectListener.onConnected();
            }else {
                connectListener.onDisconnected();
            }
        }
    }

    public synchronized void registerConnectListenter(IConnectListener listener){
        if (!connectListeners.contains(listener)){
            connectListeners.add(listener);
        }
    }

    public synchronized void unRegisterConnectListenter(IConnectListener listener){
        if (!connectListeners.contains(listener)){
            connectListeners.remove(listener);
        }
    }

    public interface IConnectListener{
        void onConnected();
        void onDisconnected();
    }

}
