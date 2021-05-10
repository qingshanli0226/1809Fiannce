package com.fiannce.bawei.user.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import android.widget.RemoteViews;


import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;
import com.fiannce.bawei.user.R;
import com.fiannce.bawei.user.UserPresenter;
import com.fiannce.bawei.user.UserView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AutoService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new AutoBinder();
    }

    public class AutoBinder extends Binder{
        public AutoService getAutoService() {
            return AutoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new UserPresenter(new UserView() {
            @Override
            public void onLoginData(LoginBean loginBean) {

            }

            @Override
            public void onRegisterData(RegisterBean registerBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                if (loginBean.getResult() != null) {
                    SpUtil.putString(AutoService.this, FiannceConstants.SP_TOKEN, loginBean.getResult().getToken());
                    FiannceUserManager.getInstance().setLogin(loginBean);
                    ARouter.getInstance().build(getString(R.string.main_mainActivity)).withInt("", 1).navigation();
                }
            }

        }).getAutoLogin(SpUtil.getString(this, FiannceConstants.SP_TOKEN));
    }

    public void setDownLoad(String url) {

        RetrofitCreator.getFiannceApiService()
                .downloadFile("http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();

                        try {
                            File file = new File(FiannceConstants.DOWNLOAD_PATH);

                            FileOutputStream fileOutputStream = new FileOutputStream(file);

                            long length = responseBody.contentLength();

                            byte[] bytes = new byte[1024];

                            int len;
                            int count = 0;

                            while ((len = inputStream.read(bytes)) != -1) {
                                count += len;

                                fileOutputStream.write(bytes, 0, len);

                                setNotification((int) length, count, false);
                            }
                            setNotification((int) length, count, true);

                            inputStream.close();
                            fileOutputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setNotification(int length, int count, boolean is) {

        Notification.Builder builder = new Notification.Builder(AutoService.this);

        builder.setContentTitle(getResources().getString(R.string.downloading));
        builder.setSmallIcon(R.drawable.icon_more_on);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_downloading);

        if (is) {
            remoteViews.setTextViewText(R.id.download_title, getResources().getString(R.string.downloadCompletes));
        }

        remoteViews.setProgressBar(R.id.download_progress, length, count, false);

        builder.setCustomContentView(remoteViews);

        builder.setCustomContentView(remoteViews);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}
