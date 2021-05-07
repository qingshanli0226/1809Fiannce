package com.fiance.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class FiannceConnectManager {

    private boolean isConnected;
    private Context context;

    private static FiannceConnectManager instance;

    private List<IConnectListener> connectListenerList = new LinkedList<>();

    private FiannceConnectManager(){

    }

    public static synchronized FiannceConnectManager getInstance() {
        if (instance==null) {
            instance = new FiannceConnectManager();
        }
        return instance;
    }


    public void init(Context applicatitonContext) {
        this.context = applicatitonContext;
        getCurrentConnectStatus();
        initReceiver();
    }

    public boolean isConnected() {
        return isConnected;
    }

    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()) {
            isConnected = true;
        } else {
            isConnected = false;
        }
    }

    private void initReceiver() {

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netConnectReceiver, intentFilter);
    }

    private BroadcastReceiver netConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                 getCurrentConnectStatus();
                notifyConnectChanged();
            }
        }
    };
    private void notifyConnectChanged() {
        for(IConnectListener listener:connectListenerList) {
            if (isConnected) {
                listener.onConnected();
            } else {
                listener.onDisconnected();
            }
        }
    }
    public synchronized void registerConnectListener(IConnectListener listener) {
        if (!connectListenerList.contains(listener)) {
            connectListenerList.add(listener);
        }
    }

    public synchronized void unRegisterConnectListener(IConnectListener listener) {
        if (connectListenerList.contains(listener)) {
            connectListenerList.remove(listener);
        }
    }
    public interface IConnectListener {
        void onConnected();
        void onDisconnected();
    }
}
