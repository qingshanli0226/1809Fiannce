package com.example.framework.mannager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedList;
import java.util.List;

public class FiannceConnectMannager {

    private boolean isConnect;//当前连接状态
    private Context context;

    private List<IConnectListener> connectListenerList = new LinkedList<>();

    private static FiannceConnectMannager mannager;

    public static synchronized FiannceConnectMannager getInstance() {
        if (mannager == null) {
            mannager = new FiannceConnectMannager();
        }
        return mannager;
    }

    public void init(Context applicatitonContext) {
        this.context = applicatitonContext;
        getCurrentConnectStatus();
        initReceiver();
    }


    //因为网络连接的状态是变化的，所以我们要监听系统网络连接的变化。系统通过广播来通知网络连接的变化
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netConnectReceiver, intentFilter);
    }

    //获取当前状态
    private void getCurrentConnectStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();//获取当前网络状态
        if (networkInfo != null && networkInfo.isConnected()) {//如果有连接
            isConnect = true;
        } else {
            isConnect = false;
        }
    }

    private BroadcastReceiver netConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果收到网络连接通知的广播
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                getCurrentConnectStatus();
                //通知各个页面刷新ui
                notifyConnectChanged();
            }
        }
    };

    //回调各个页面注册接口  通知网络变化
    private void notifyConnectChanged() {
        for (IConnectListener listener : connectListenerList) {
            if (isConnect) {
                listener.onConnected();
            } else {
                listener.onDisConnected();
            }
        }
    }


    public synchronized void RegisterConnectListener(IConnectListener listener) {
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

        void onDisConnected();
    }
}

