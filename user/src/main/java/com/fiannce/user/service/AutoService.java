package com.fiannce.user.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.framework.manager.CacheUserManager;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.user.R;
import com.fiannce.user.register.IUserView;
import com.fiannce.user.register.UserPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.fiannce.commond.CommonConstant.DOWNLOAD_PATH;

public class AutoService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new AutoBinder();
    }

    public class AutoBinder extends Binder{
        public AutoService getAutoService() {
            return AutoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new UserPresenter(new IUserView() {
            @Override
            public void onRegister(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                if (loginBean.getResult() != null) {
                    SpUtil.putString(AutoService.this, CommonConstant.SP_TOKEN, loginBean.getResult().getToken());
                    CacheUserManager.getInstance().setLoginBean(loginBean);
//                FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                    ARouter.getInstance().build(getString(R.string.main_mainActivity)).navigation();
                }
            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showToast(String error) {

            }
        }).getAutoLogin(SpUtil.getString(this, CommonConstant.SP_TOKEN));

    }

    public void setDownLoad(String url) {

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
                            File file = new File(DOWNLOAD_PATH);

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

                            inputStream.close();
                            fileOutputStream.close();
//                            if (isApplicationUsed()){
//                                window();
//                            }else {
//                                Toast.makeText(AutoService.this, "当前应用未打开", Toast.LENGTH_SHORT).show();
//                            }
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

    public void setNotification(int length, int count, boolean is) {

        Notification.Builder builder = new Notification.Builder(AutoService.this);

        builder.setContentTitle(getResources().getString(R.string.downloading));
        builder.setSmallIcon(R.drawable.icon_more_on);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_downloading);

        if (is) {
            remoteViews.setTextViewText(R.id.download_title, getResources().getString(R.string.downloadCompletes));
        }

        remoteViews.setProgressBar(R.id.download_progress, length, count, false);

        builder.setCustomContentView(remoteViews);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button install;

    public void window(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //设置小窗口尺寸的类
        layoutParams = new WindowManager.LayoutParams();
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
        final View rootView = LayoutInflater.from(this).inflate(R.layout.item_install, null);
        windowManager.addView(rootView, layoutParams);

        install = rootView.findViewById(R.id.window_install);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAPK(DOWNLOAD_PATH);
                Toast.makeText(AutoService.this, "安装成功", Toast.LENGTH_SHORT).show();
                windowManager.removeView(rootView);
            }
        });
//        windowManager.addView(rootView, layoutParams);
    }

    private void openAPK(String fileSavePath) {

        File file = new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            data = FileProvider.getUriForFile(this, "com.example.myapplication", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    //检查当前我们的应用是否用户正在使用
    private boolean isApplicationUsed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //Androi系统里所有的应用都被ActivityMananger管理，它可以获取所有的应用列表
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        //遍历应用列表，比较每个应用程序包名和我们应用的包名是否一致，如果一致就代表找到了我们的应用
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo:runningAppProcessInfoList) {
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
}
