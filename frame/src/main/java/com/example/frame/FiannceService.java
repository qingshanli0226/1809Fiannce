package com.example.frame;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.example.net.RetrofitManager;

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
   public class FiannceBinder extends Binder{
        public FiannceService getFiannceService(){
            return FiannceService.this;
        }
   }

public void DownLoad(String url){
    LogUtils.d("132");
        RetrofitManager.getApi()
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
                        File file = new File(CommonConstant.DOWNLOAD_PATH);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            long l = responseBody.contentLength();
                            byte[] bytes= new byte[1024];
                            int len;
                            int count=0;
                            while ((len=inputStream.read(bytes))!=-1){
                                  count+=len;
                                  fileOutputStream.write(bytes,0,len);
                                   setNotification((int) l,count,false);
                            }
                            setNotification((int) l,count,true);
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
public void setNotification(int lenth,int count,boolean is){
    Notification.Builder builder = new Notification.Builder(FiannceService.this);
    builder.setContentTitle("下载中");
    builder.setSmallIcon(R.drawable.wc_ac_08);
    RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_download);
    if (is){
        remoteViews.setTextViewText(R.id.download_title,"下载完事");
    }
    remoteViews.setProgressBar(R.id.download_progress,lenth,count,false);
    builder.setCustomContentView(remoteViews);
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(1,builder.build());
    }
}
