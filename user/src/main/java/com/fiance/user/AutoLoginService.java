package com.fiance.user;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fiance.net.RetrofitCreator;

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
    private int count = 0;
    private Context mContext;
    private NotificationManager mNotificationManager;
    private long totaBute=100;
    private float pregress=0;


    public class MyBinder extends Binder{
        public void myMethod(String path){
            startDownLoad(path);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private void startDownLoad(String path) {
        Log.d("AutoLoginService", "1");
        mContext = this;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(mContext)
                .setContentTitle("正在下载")
                .setContentText((int) pregress + "%")
                .setProgress((int) totaBute, 0, false)
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        mNotificationManager.notify(2, notification.build());

        Log.d("AutoLoginService", "2");
        String apkName = "youdao.apk";
        RetrofitCreator.getFiannceApiService().downloadFile(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        Log.d("AutoLoginService", "3");
                        FileOutputStream fos = null;
                        File instanll = new File("/sdcard/" + apkName);
                        if (instanll.exists()) {
                            instanll.delete();
                        }
                        try {
                            totaBute = responseBody.contentLength();
                            int len = -1;
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = responseBody.byteStream();
                            fos = new FileOutputStream(instanll);
                            while ((len = inputStream.read(bytes)) != -1) {
                                Log.d("AutoLoginService", "len:" + len);
                                fos.write(bytes, 0, len);
                                count += len;
                                pregress = (float) count / (float) totaBute * 100;
                                Log.d("AutoLoginService", "6");
                                Log.d("AutoLoginService", totaBute + "   " + count);
                                notification.setProgress((int) totaBute, (int) count, false);
                                notification.setContentText((int) pregress + "%");
                                mNotificationManager.notify(2, notification.build());
                            }
                            Log.d("AutoLoginService", "5");
                            Toast.makeText(AutoLoginService.this, "下载完成", Toast.LENGTH_SHORT).show();
                            mNotificationManager.notify(2, notification.build());

                            inputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
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
}
