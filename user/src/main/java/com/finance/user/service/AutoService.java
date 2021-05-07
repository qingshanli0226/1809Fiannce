package com.finance.user.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.finance.framework.manager.CacheUserManager;
import com.finance.framework.sp.Constant;
import com.finance.framework.sp.SPUtil;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;
import com.finance.net.model.RetrofitCreator;
import com.finance.user.R;
import com.finance.user.register.mvp.IRegisterView;
import com.finance.user.register.mvp.RegisterPresenter;

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
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new FiannceBinder();
    }

    public class FiannceBinder extends Binder {
        public AutoService getFiannceService() {
            return AutoService.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new RegisterPresenter(new IRegisterView() {
            @Override
            public void onRegisterData(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                if( loginBean.getResult() != null){
                    SPUtil.putString(AutoService.this, "token",loginBean.getResult().getToken());
                    //跳到主页面返回
                    CacheUserManager.getInstance().setLoginBean(loginBean);
                }

            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showError(String msg) {

            }
        }).getAutoLogin(SPUtil.getString(this, "token"));
        return super.onStartCommand(intent, flags, startId);
    }

    public void DownLoad(String url) {
        //http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk
        RetrofitCreator.getFinanceApiService()
                .downloadFile("http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();

                        try {
                            File file = new File(Constant.DOWNLOAD_PATH);

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

        Notification.Builder builder = new Notification.Builder(AutoService.this);

        builder.setContentTitle(getResources().getString(R.string.downloading));
        builder.setSmallIcon(R.drawable.icon_more_on);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_download);

        if (is) {
            remoteViews.setTextViewText(R.id.download_title, getResources().getString(R.string.downloadCompletes));
        }

        remoteViews.setProgressBar(R.id.download_progress, length, count, false);

        builder.setCustomContentView(remoteViews);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}