package com.example.framework;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.TextView;
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

    private Notification.Builder builder;
    private Handler handler = new Handler();

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

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Window();
                                }
                            });

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

        if (builder==null){
            builder = new Notification.Builder(FiannceService.this);
        }

        builder.setContentTitle(getResources().getString(R.string.downloading));
        builder.setSmallIcon(R.drawable.icon_more_on);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_download);

        if (is) {
            LogUtils.d("123----"+is);
            remoteViews.setTextViewText(R.id.download_title, getResources().getString(R.string.downloadCompletes));
        }

        remoteViews.setProgressBar(R.id.download_progress, length, count, false);

        builder.setCustomContentView(remoteViews);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (is){
//            manager.notify(2, builder.build());
//        }else {
            manager.notify(1, builder.build());
//        }
    }

    public void Window(){
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //设置小窗口尺寸的类
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        //设置窗口的类型为系统类型，系统类型的窗口显示应用窗口的上方.系统窗口可以在Service中显示,普通Dialog不可以的
        layoutParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //像素格式为透明的
        layoutParams.format = PixelFormat.TRANSPARENT;

        //设置该flag在显示该小窗口时，其他窗口的按钮或者其他控件都可以点击.
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置小窗口的尺寸
        //单位是像素
        layoutParams.width=700;
        layoutParams.height=500;

        //生成一个窗口的布局view，并且将该view添加到窗口里.
        View rootView = LayoutInflater.from(this).inflate(R.layout.window_ijk, null);
        windowManager.addView(rootView, layoutParams);


//        ijkVideoView = rootView.findViewById(R.id.ijkVideoView);
//        rootView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("LQS", "position:" + ijkVideoView.getCurrentPosition());
//                ijkVideoView.stopPlayback();
//                windowManager.removeView(rootView);
//            }
//        });
    }

}
