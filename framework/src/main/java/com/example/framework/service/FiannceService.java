package com.example.framework.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.R;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;

import java.io.File;
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
    public void onCreate() {
        super.onCreate();

    }


    public void DownloadApk(String url) {
        Log.i("wpppp", "DownloadApk: "+url);
        RetrofitCreator.getFiannceApiService().downloadFile("http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk")
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
                            File file = new File(FianceConstants.SD_DOWNLOAD);
                            FileOutputStream fileOutputStream = new FileOutputStream(file);

                            long length = responseBody.contentLength();

                            byte[] bytes = new byte[1024];
                            int len;
                            int count = 0;

                            while ((len = inputStream.read(bytes)) != -1) {
                                count += len;
                                fileOutputStream.write(bytes, 0, len);
                                addNotification((int) length, count,"正在更新金融...",count*100/length+"%");
                            }
                            inputStream.close();
                            fileOutputStream.close();

                            addNotification((int) length, count,"下载完毕","");

                            Intent intent = new Intent(Intent.ACTION_VIEW);

//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //android N的权限问题
//                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//授权读权限
//                                Uri contentUri = FileProvider.getUriForFile(FiannceService.this, "com.nongyan.xinzhihouse.fileprovider", new File(FianceConstants.SD_DOWNLOAD, "ZhouzhiHouse.apk"));//注意修改
//                                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//                            } else {
//                                intent.setDataAndType(Uri.fromFile(new File(FianceConstants.SD_DOWNLOAD, "ZhouzhiHouse.apk")), "application/vnd.android.package-archive");
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            }
//                            startActivity(intent);

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

    private void addNotification(int length, int count,String title,String percent) {
        Notification.Builder builder = new Notification.Builder(FiannceService.this);
        builder.setSmallIcon(android.R.drawable.star_big_on);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_view);
        remoteViews.setProgressBar(R.id.server_nf_pb, length, count, false);

        remoteViews.setTextViewText(R.id.server_nf_title,title);
        remoteViews.setTextViewText(R.id.server_nf_text,percent);
        remoteViews.setImageViewResource(R.id.server_nf_img,R.drawable.wc_ac_01);
        builder.setCustomContentView(remoteViews);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String token = SpUtil.getString(this, FianceConstants.TOKEN_KEY);
        RetrofitCreator.getFiannceApiService().getAutoLoginData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")) {
                            FiannceUserManager.getInstance().setLoginBean(loginBean);
                            SpUtil.setString(FiannceService.this, FianceConstants.TOKEN_KEY, loginBean.getResult().getToken());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }
}
