package com.fiannce.user.autologin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.fiannce.common.SpUtiles;

import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.AutoLoginBean;
import com.fiannce.net.mode.UserBean;
import com.fiannce.user.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AutoLoginService extends Service {
    public AutoLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public AutoLoginService getAutoLoginService() {
            return AutoLoginService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.json("开启服务");
        String string = SpUtiles.getString(AutoLoginService.this);

        if (string.equals("")) {
        } else {
            new AutoLoginPresenter(new IAutoLoginView() {
                @Override
                public void AutoLogin(AutoLoginBean autoLoginBean) {
                    if (autoLoginBean.getCode().equals("200")) {
                        AutoLoginBean.ResultBean result = autoLoginBean.getResult();
                        String token = result.getToken();
                        LogUtils.json(token);
                        SpUtiles.putString(AutoLoginService.this, token);
                        SharedPreferences sharedPreferences = getSharedPreferences("login.txt", MODE_PRIVATE);
                        String name = sharedPreferences.getString("name", "");
                        UserBean userBean = new UserBean();
                        userBean.setName(name);
                        EventBus.getDefault().post(userBean);
                        Toast.makeText(AutoLoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void showLoading() {

                }

                @Override
                public void hideLoading() {

                }

                @Override
                public void showError(String error) {

                }
            }).getAutoLogin(SpUtiles.getString(AutoLoginService.this));
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void setDownLoad(String url) {
        RetrofitCreator.getFiannceApiService()
                .downloadFile("http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        try {
                            File file = new File("/sdcard/Download/1809Fiannce.apk");
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
    public void setNotification(int length, int count, boolean is) {
        Notification.Builder builder = new Notification.Builder(AutoLoginService.this);
        builder.setContentTitle("");
        builder.setSmallIcon(R.mipmap.app_icon);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_downloading);
        if (is) {
            remoteViews.setTextViewText(R.id.download_title, "下载完成");
        }
        remoteViews.setProgressBar(R.id.download_progress, length, count, false);
        builder.setCustomContentView(remoteViews);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }


}
