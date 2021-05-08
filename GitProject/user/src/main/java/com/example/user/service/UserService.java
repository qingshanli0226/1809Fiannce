package com.example.user.service;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.login.LoginActivity;
import com.example.user.register.IUserView;
import com.example.user.register.UserPresenter;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.sax.TemplatesHandler;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class UserService extends Service {
    public UserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public UserService getMyService() {
            return UserService.this;
        }
    }

    //自动登录
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!SpUtil.getString(this, CommonConstant.SP_TOKEN).equals("")) {
            RetrofitManager.getHttpApiService()
                    .getAutoLogin(SpUtil.getString(this, CommonConstant.SP_TOKEN))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LoginBean loginBean) {
                            if (loginBean.getResult() != null) {
                                SpUtil.putString(UserService.this, CommonConstant.SP_TOKEN, loginBean.getResult().getToken());
                                //跳到主页面返回
                                CacheUserManager.getInstance().setLoginBean(loginBean);
//                FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
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
//        windon();
        return super.onStartCommand(intent, flags, startId);
    }


    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void downLoad(String url, Handler handler) {

        File instanll = new File(CommonConstant.INSTANLL);
        if (instanll.exists()) {
            windon();
            return;
        }
        //通道
        NotificationChannel channel = new NotificationChannel("channel_1", getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
        //通知
        Notification.Builder builder = new Notification.Builder(this, "channel_1");
        builder.setSmallIcon(R.drawable.ssdk_oks_ptr_ptr);
        builder.setProgress(100, 0, false);
        builder.setContentTitle(getString(R.string.service_title));
        builder.setAutoCancel(true);

//        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, ), PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(activity);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        notificationManager.notify(666, builder.build());

        //下载
        RetrofitManager.getHttpApiService()
                .getDownLoad(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody requestBody) {
                        InputStream inputStream = null;
                        FileOutputStream fileOutputStream = null;
//                        File instanll = new File(CommonConstant.INSTANLL);

                        try {

                            //一共长度
                            long totalByte = requestBody.contentLength();
                            int len = -1;
                            int count = 0;
                            byte[] bytes = new byte[1024];
                            inputStream = requestBody.byteStream();
                            fileOutputStream = new FileOutputStream(instanll);
                            while ((len = inputStream.read(bytes)) != -1) {
                                //对话框信息
//                                Message obtain = Message.obtain();
//                                obtain.what = 7;
//                                obtain.obj = curr;
//                                handler.sendMessage(obtain);
                                fileOutputStream.write(bytes, 0, len);
                                //更新进度
                                count += len;
                                builder.setProgress((int) totalByte, count, false);
                                notificationManager.notify(666, builder.build());
                            }
                            //完成
                            builder.setContentTitle(getString(R.string.service_title_finish));
                            builder.setProgress((int) totalByte, count, false);
                            notificationManager.notify(666, builder.build());

                            SpUtil.putBoolean(CommonConstant.INSTANLL_NAME, context, CommonConstant.INSTANLL_FLAG, true);

                            windon();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void openAPK(String fileSavePath) {
        Toast.makeText(context, "aaa", Toast.LENGTH_SHORT).show();

        File file = new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            data = FileProvider.getUriForFile(this, "com.example.gitproject", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);

    }


    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View rootView;
    private void windon() {
        //判断是否可以安装
        if (isApplicationUsed() && SpUtil.getBoolean(CommonConstant.INSTANLL_NAME, context,CommonConstant.INSTANLL_FLAG)) {
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            //设置小窗口尺寸的类
            layoutParams = new WindowManager.LayoutParams();
            //设置窗口的类型为系统类型，系统类型的窗口显示应用窗口的上方.系统窗口可以在Service中显示,普通Dialog不可以的
            layoutParams.type= WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
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
            rootView = LayoutInflater.from(this).inflate(R.layout.window_itl, null);
            windowManager.addView(rootView, layoutParams);
            rootView.findViewById(R.id.window_install).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "安装", Toast.LENGTH_SHORT).show();
                    //            openAPK(CommonConstant.INSTANLL);

                }
            });

        } else{

        }
    }

    //检查当前我们的应用是否用户正在使用
    private boolean isApplicationUsed() {
        ActivityManager systemService = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = systemService.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if(runningAppProcess.processName.equals(getPackageName())){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}