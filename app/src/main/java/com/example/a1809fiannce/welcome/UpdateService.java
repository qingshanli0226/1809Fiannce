package com.example.a1809fiannce.welcome;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.a1809fiannce.R;
import com.example.network.retrofit.RetrofitManager;

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

public class UpdateService extends Service {
    public UpdateService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends Binder {
        public UpdateService MyService(){
            return UpdateService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void DownUpdate(String url){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载中");
//        builder.setPriority(Notification.PRIORITY_MAX);
//        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setProgress(100,0,false);
        manager.notify(1,builder.build());
            RetrofitManager
                    .getRetrofit()
                    .downloadFile(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull ResponseBody responseBody) {
                            InputStream inputStream = null;
                            FileOutputStream fileOutputStream = null;
                            File file = new File("/sdcard/Download/a.apk");
                            if (file.exists()) {
                                return;
                            }
                            long length = responseBody.contentLength();
                            Log.i("aa", "onNext: "+length);
                            int i = -1;
                            int count = 0;
                            byte[] bytes = new byte[1024];
                            inputStream = responseBody.byteStream();
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                while ((i = inputStream.read(bytes)) != -1) {
                                    fileOutputStream.write(bytes, 0, i);
                                    int current = (int) (length / i);
                                    count += i*50;
                                    Log.i("aa", "onNext: "+current);
                                    if (count>=length){
                                        builder.setContentTitle("下载完成");
                                        manager.notify(1, builder.build());
                                    }
                                    builder.setProgress((int) length, count, false);
                                    manager.notify(1, builder.build());

                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.i("zx", "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

}