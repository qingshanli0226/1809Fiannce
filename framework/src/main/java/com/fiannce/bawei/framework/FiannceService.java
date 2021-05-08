package com.fiannce.bawei.framework;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.example.commom.FiannceContants;
import com.example.framework.R;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.NetModel;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.model.LoginBean;

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

import static android.content.ContentValues.TAG;

public class FiannceService extends Service {
    private  String string;
    public FiannceService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new FiannceBinder();
    }

    public class FiannceBinder extends Binder{

        public FiannceService getFiannceService(){
            return FiannceService.this;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = SpUtil.getString(FiannceService.this, FiannceContants.TOKEN_KEY);
        Log.d(TAG, "onStartCommand: "+string);
        if (!string.equals("")) {
            Log.i(TAG, "onStartCommand: ");
            RetrofitCreator.getfiannceApiService()
                    .getToken(string)
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

    public void DownLoad(String url){

        RetrofitCreator.getfiannceApiService()
                .downloadFile(" http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }


                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(ResponseBody responseBody) {

                        InputStream inputStream = responseBody.byteStream();


                        try {

                            File file = new File(FiannceContants.DOWNLOAD_PATH);

                            FileOutputStream fileOutputStream = new FileOutputStream(file);

                            long length = responseBody.contentLength();
                            byte[] bytes = new byte[1024];
                            int len ;
                            int count = 0;

                            while ((len = inputStream.read(bytes)) != -1){
                                count += len;

                                fileOutputStream.write(bytes,0,len);
                                setNotification((int) length,count,false);
                            }

                            setNotification((int) length,count,true);

                            inputStream.close();
                            fileOutputStream.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setNotification(int length, int count, boolean is){

        Notification.Builder builder = new Notification.Builder(FiannceService.this);
        builder.setContentTitle(getResources().getString(R.string.down));
        builder.setSmallIcon(R.drawable.tou);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_download);

        if (is){
            remoteViews.setTextViewText(R.id.down_title,getResources().getString(R.string.download));
        }

        remoteViews.setProgressBar(R.id.down_progress,length,count,false);
        //builder.setProgress(100,10,false);

        builder.setCustomContentView(remoteViews);


        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        systemService.notify(1,builder.build());
    }



}
