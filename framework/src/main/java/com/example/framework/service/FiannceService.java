package com.example.framework.service;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        Log.i("wpppp", "DownloadApk: " + url);
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

                            initNotification((int) length, count, "正在更新金融...", count * 100 / length + "%", 1);
                            while ((len = inputStream.read(bytes)) != -1) {
                                count += len;
                                fileOutputStream.write(bytes, 0, len);
                                initNotification((int) length, count, "正在更新金融...", count * 100 / length + "%", 1);
                            }
                            inputStream.close();
                            fileOutputStream.close();

                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            initNotification((int) length, count, "下载完毕", "", 1);
                            SpUtil.setUpdateApk(FiannceService.this, FianceConstants.APK_UPDATE, true);
                            if (isApplicationUsed()) {
                                Log.i("wppp", "onNext:下载完毕 + 是自己的应用");

                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showSystemWindow();
                                    }
                                });
                            }


//                            Intent intent = new Intent(Intent.ACTION_VIEW);
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


    private void initNotification(int length, int count, String title, String percent, int versions) {
        builder = new Notification.Builder(FiannceService.this);
        builder.setSmallIcon(android.R.drawable.star_big_on);
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification_view);
        remoteViews.setProgressBar(R.id.server_nf_pb, length, count, false);

        remoteViews.setTextViewText(R.id.server_nf_title, title);
        remoteViews.setTextViewText(R.id.server_nf_text, percent);
        remoteViews.setImageViewResource(R.id.server_nf_img, R.drawable.wc_ac_01);
        builder.setCustomContentView(remoteViews);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(versions, builder.build());
    }

    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private RemoteViews remoteViews;

//    public void refreshNotification(int length, int count, String title, String percent, int versions) {
//        remoteViews.setProgressBar(R.id.server_nf_pb, length, count, false);
//        remoteViews.setTextViewText(R.id.server_nf_title, title);
//        remoteViews.setTextViewText(R.id.server_nf_text, percent);
//        remoteViews.setImageViewResource(R.id.server_nf_img, R.drawable.wc_ac_01);
//        notificationManager.notify(versions, builder.build());
//    }


    //检查当前我们的应用是否用户正在使用

    private boolean isApplicationUsed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //Androi系统里所有的应用都被ActivityMananger管理，它可以获取所有的应用列表
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        //遍历应用列表，比较每个应用程序包名和我们应用的包名是否一致，如果一致就代表找到了我们的应用
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfoList) {
            if (runningAppProcessInfo.processName.equals(getPackageName())) {
                //判断当前我们的应用是否是前台进程，如果是代表着用户正在操作我们的应用
                if (runningAppProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private void showSystemWindow() {
        //注意此处传入的是Applcation的上下文，所以说明系统窗口不依赖任何的窗口
        final WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        // 注意要设置此属性，不然其它的View无法获取焦点
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;//这个地方是重点，设置窗口的类型

        //通过布局加载器加载布局文件
        final View mView = LayoutInflater.from(this).inflate(R.layout.pop_dsuccess, null);

        wm.addView(mView, params);//调用WindowMangerImpl添加窗口

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
