package com.example.user.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.net.FiannceHttpMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;

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

public class FiannceService extends Service {

    private int count = 0;
    private Context mContext;
    private NotificationManager mNotificationManager;
//    private Notification notification;
    private long totaBute=100;//总长度
    private float pregress=0;//现在的长度
    private File instanll;

    @Override
    public void onCreate() {
        super.onCreate();

        new MorePresenter(new MoreView() {
            @Override
            public void initRegister(RegisterBean registerBean) {

            }

            @Override
            public void initLogin(LoginBean loginBean) {

            }

            @Override
            public void initAuto(AutoBean autoBean) {
                if (autoBean.getCode().equals("200")) {
                    SpUtil.setString(FiannceService.this, autoBean.getResult().getToken());
                    Log.d("AutoLoginService", "自动登陆成功");
                    FiannceUserMannager.getInstance().setIsLwogin(true);
                } else {
                    Log.d("AutoLoginService", "自动登陆失败");
                    FiannceUserMannager.getInstance().setIsLwogin(false);
                }
            }

            @Override
            public void initLogout(LogoutBean logoutBean) {

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
        }).getAuto(SpUtil.getString(this, FiannceConstants.TOKEN_KEY));
    }

    public FiannceService() {
    }


    //代理人的类
    public class MyBinder extends Binder {
        public void myMethod(String path) {
            startDownLoad(path);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //下载
    private void startDownLoad(String path) {
//        systemPop();
        mContext = this;
        //下载进度通知
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(mContext)
                .setContentTitle("正在下载")
                .setContentText((int)pregress+"%")
                .setProgress((int) totaBute, 0, false)
                .setSmallIcon(R.drawable.left);

        mNotificationManager.notify(2, notification.build());

        Log.d("AutoLoginService", "zkh2");
        String apkName = "youdao.apk";
        FiannceHttpMannager.getApiModel().DownLoadApk(path)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        Log.d("AutoLoginService", "zkh3");
                        FileOutputStream fos = null;
                         instanll = new File("/sdcard/Download/" + apkName);
                        if (instanll.exists()) {
                            instanll.delete();
//                            return;
                        }
                        try {
                            //总长度
                            totaBute = responseBody.contentLength();
                            int len = -1;
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = responseBody.byteStream();
                            fos = new FileOutputStream(instanll);
                            while ((len = inputStream.read(bytes)) != -1) {
                                Log.d("AutoLoginService", "len:" + len);
                                fos.write(bytes, 0, len);
                                count += len;
                               pregress =  (float)count/(float) totaBute  * 100;
                                Log.d("AutoLoginService", "6");
//                                Thread.sleep(200);
//                                handler.sendEmptyMessage(3);
                                Log.d("AutoLoginService", totaBute + "   " + count);
                                notification.setProgress((int) totaBute, (int) count, false);
                                notification.setContentText((int)pregress+"%");
                                mNotificationManager.notify(2,notification.build());
                            }
                            Log.d("AutoLoginService", "zkh5");

                            mNotificationManager.notify(2,notification.build());
                            //是否在前台
                            if (isApplicationUsed()){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.sendEmptyMessage(1);
                                    }
                                });
                            }else {
                                Toast.makeText(FiannceService.this, "下载成功", Toast.LENGTH_SHORT).show();
                            }
                            inputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
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
    //判断是否在前台
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

     public void systemPop(){
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
         View rootView = LayoutInflater.from(this).inflate(R.layout.download_systempop, null);
         windowManager.addView(rootView, layoutParams);

         rootView.findViewById(R.id.nowDownload).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openApk(instanll.getPath());
                 windowManager.removeViewImmediate(rootView);
             }
         });
         rootView.findViewById(R.id.noNowDownload).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 windowManager.removeViewImmediate(rootView);
             }
         });
     }
//安装
    private void openApk(String fileSavePath) {

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

    private Handler handler=new Handler(){
         @Override
         public void handleMessage(@androidx.annotation.NonNull Message msg) {
             super.handleMessage(msg);
             if (msg.what==1){
                 systemPop();
                 }
         }
     };


}