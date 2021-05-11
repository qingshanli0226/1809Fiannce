package com.fiannce.bawei.framework;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RemoteViews;


import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

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
    private FileOutputStream fileOutputStream;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Handler handler = new Handler();
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



                    @Override
                    public void onNext(ResponseBody responseBody) {

                        InputStream inputStream = responseBody.byteStream();


                        try {

                            File file = new File(FiannceContants.DOWNLOAD_PATH);

                             fileOutputStream = new FileOutputStream(file);

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

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    showLittleWindow();
                                }
                            });



                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {

                                try {
                                    if (inputStream != null){
                                    inputStream.close();
                                    }

                                    if (fileOutputStream != null){
                                        fileOutputStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

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

    private void showLittleWindow() {
        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //设置小窗口尺寸的类
         layoutParams = new WindowManager.LayoutParams();
        //设置窗口的类型为系统类型，系统类型的窗口显示应用窗口的上方.系统窗口可以在Service中显示,普通Dialog不可以的
        layoutParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                                                    //.TYPE_APPLICATION_OVERLAY;

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
        final View rootView = LayoutInflater.from(this).inflate(R.layout.little_window, null);
        rootView.findViewById(R.id.que).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                install();
                windowManager.removeView(rootView);
            }
        });
        rootView.findViewById(R.id.qu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowManager.removeView(rootView);
            }
        });
        windowManager.addView(rootView, layoutParams);
    }

    public void install(){
        File file = new File(Uri.parse(FiannceContants.DOWNLOAD_PATH).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            data = FileProvider.getUriForFile(this, "com.example.designed", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "applicationnd.android.package-archive");
        startActivity(intent);
    }

}
