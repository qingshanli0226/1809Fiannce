package com.example.framework;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.example.commom.FiannceConstants;
import com.example.commom.SpUtil;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.NetModel;
import com.example.net.RetrofitCreator;
import com.example.net.mode.AutoLoginBean;
import com.example.net.mode.LoginBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FiannceService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new FiannceBinder();
    }

    public class FiannceBinder extends Binder {
        public FiannceService getFiannceService() {
            return FiannceService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = SpUtil.getString(FiannceService.this, FiannceConstants.TOKEN_KEY);

        LogUtils.e(string);
        if (!string.equals("")) {
            RetrofitCreator.getFiannceApiService()
                    .getAuto(string)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LoginBean loginBean) {
                            if (loginBean.getCode().equals("200")) {
                                FiannceUserManager.getInstance()
                                        .setLoginBean(loginBean);

                                SpUtil.setString(NetModel.context, loginBean.getResult().getToken());
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
        return super.onStartCommand(intent, flags, startId);
    }

    public void DownLoad(String url) {
        //http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk
        LogUtils.d("123");
        RetrofitCreator.getFiannceApiService()
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
                        LogUtils.d(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setNotification(int length, int count, boolean is) {
        LogUtils.d(is);

        Notification.Builder builder = new Notification.Builder(FiannceService.this);

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
