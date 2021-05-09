package com.example.a1809fiannce.welcome;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

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
                                Toast.makeText(UpdateService.this, "文件已存在,无需下载", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            long length = responseBody.contentLength();
                            Log.i("aa", "length: "+length);
                            int i = -1;
                            int count = 0;
                            byte[] bytes = new byte[1024];
                            inputStream = responseBody.byteStream();
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                while ((i = inputStream.read(bytes)) != -1) {
                                    fileOutputStream.write(bytes, 0, i);
                                    count += i;
                                    builder.setProgress((int) length, count, false);
                                    manager.notify(1, builder.build());
                                    Log.i("aa", "count: "+count);
                                    if (count>=length){
                                        builder.setContentTitle("下载完成");
                                        manager.notify(1, builder.build());
                                        SharedPreferences sharedPreferences = getSharedPreferences("install", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sharedPreferences.edit();
                                        edit.putBoolean("isInstall",true);
                                        edit.commit();
                                        Intent intent = new Intent();
                                        intent.setAction("complete");
                                        sendBroadcast(intent);
                                        return;
                                    }

                                }
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
                            Log.i("zx", "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

}