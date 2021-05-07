package com.example.user.autologin;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.demo.Demo;
import com.example.framework.manager.FiannceUserManager;
import com.example.model.AutoLoginBean;
import com.example.net.RetrofitCretor;
import com.example.sp.SpUtils;
import com.example.user.R;

import java.io.File;
import java.io.FileNotFoundException;
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

import static com.example.demo.Demo.CONFIRM_DIALOG_MESSAGE;
import static com.example.demo.Demo.DOWNLOAD_FINISH;

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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //在封装的sp里面拿出token判断是否为空，不为空的话执行继续登录
        if (!SpUtils.getString(UserService.this).equals("")){
            new PersonAutoLoginPresenter(new IPersonLoginAutoView() {
                @Override
                public void autoLogin(AutoLoginBean autoLoginBean) {
                    LogUtils.json(autoLoginBean);
                    //先判断code是否为200,如果登陆成功,调用USERMANGER改变全局登录状态
                    if (autoLoginBean.getCode().equals("200")){
                        FiannceUserManager.getInstance().setLogin(true);
                        Toast.makeText(UserService.this, "自动登录成功"+FiannceUserManager.getInstance().isLogin(), Toast.LENGTH_SHORT).show();
                        SpUtils.putString(UserService.this,autoLoginBean.getResult().getToken());
                    }
                }

                @Override
                public void showLoading() {

                }

                @Override
                public void hideLoading() {

                }

                @Override
                public void showError(String error) {

                }
            }).postAutoLogin(SpUtils.getString(this));
        }


        return super.onStartCommand(intent, flags, startId);
    }
x
    public void downLoad(String url){
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_more_on);
        builder.setProgress(100,0,false);
        builder.setContentTitle(CONFIRM_DIALOG_MESSAGE);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(Notification.PRIORITY_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());

        RetrofitCretor.getFiannceApiService()
                .downLoadFile(url)
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
                        File file = new File("/sdcard/Download/aaa.apk");

                        try {
                            long totalByte  = responseBody.contentLength();
                            int len = -1;
                            byte[] bytes = new byte[1024];
                            int count = 0;
                            inputStream = responseBody.byteStream();
                            fileOutputStream = new FileOutputStream(file);

                            while ((len = inputStream.read(bytes))!=-1){
                                fileOutputStream.write(bytes,0,len);
                                Log.i("zrfs", "onNexttotalByte: "+totalByte);
                                Log.i("zrfs", "onNextlen: "+len);

                                long curr = totalByte / len;
                                count+=len;
                                Log.i("zrf", "onNext: "+curr);
                                builder.setProgress((int) totalByte, count,false);
                                manager.notify(2,builder.build());
                            }
                            builder.setContentTitle(DOWNLOAD_FINISH);
                            manager.notify(2,builder.build());

//                            start7Install();



                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zrf", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void start7Install() {
        File imagePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/sdcard/Download/aaa.apk");
        File newFile = new File(imagePath, "/sdcard/Download/aaa.apk");
        Uri apkUri = FileProvider.getUriForFile(UserService.this, "cn.test.zz.fileprovider", newFile);//在AndroidManifest中的android:authorities值
        Intent install =new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        startActivity(install);
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
            View rootView = LayoutInflater.from(this).inflate(R.layout.window_ijk, null);
            windowManager.addView(rootView, layoutParams);


            install = rootView.findViewById(R.id.window_install);
            install.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UserService.this, "安装成功", Toast.LENGTH_SHORT).show();
                    windowManager.removeView(rootView);
                }
            });
            windowManager.addView(rootView, layoutParams);
        }

}