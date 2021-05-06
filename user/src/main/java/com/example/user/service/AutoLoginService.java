package com.example.user.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.net.FiannceHttpMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;

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

public class AutoLoginService extends Service {

    private int count = 0;
    private Context mContext;
    private NotificationManager mNotificationManager;
//    private Notification notification;
    private long totaBute=100;//总长度
    private float pregress=0;//现在的长度

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@androidx.annotation.NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1://下载失败
//                    notification.contentView.setTextViewText(R.id.content_view_text1, "下载失败，请重试");
//                    notification.flags = Notification.FLAG_AUTO_CANCEL; // 设置为自动取消
//                    mNotificationManager.notify(1, notification);
//                    AutoLoginService.this.stopSelf();
//                    break;
//                case 2://下载完成
//
//                case 3://下载中 更新进度
//                    Log.d("AutoLoginService", "zkh 刷新");
//                    notification.contentView.setProgressBar(R.id.content_view_progress, (int) totaBute, (int) pregress, false);
//                    mNotificationManager.notify(1, notification);
//                    break;
//            }
//
//
//        }
//    };

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
                    SpUtil.setString(AutoLoginService.this, autoBean.getResult().getToken());
                    Log.d("AutoLoginService", "自动登陆成功");
                    FiannceUserMannager.getInstance().setIsLwogin(true);
                } else {
                    Log.d("AutoLoginService", "自动登陆失败");
                    FiannceUserMannager.getInstance().setIsLwogin(false);
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
        }).getAuto(SpUtil.getString(this, FiannceConstants.TOKEN_KEY));
    }

    public AutoLoginService() {
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
        Log.d("AutoLoginService", "zkh1");
        mContext = this;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(mContext)
                .setContentTitle("正在下载")
                .setContentText((int)pregress+"%")
                .setProgress((int) totaBute, 0, false)
                .setSmallIcon(R.drawable.left);

        mNotificationManager.notify(2, notification.build());

        Log.d("AutoLoginService", "zkh2");
        String apkName = "youdao.apk";
//        File tmpFile=new File("/sdcard/youdao/");
//        if (!tmpFile.exists()){
//            tmpFile.mkdir();
//        }
//        File file=new File("/sdcard/youdao/"+apkName);
//        try {
//            URL url=new URL(path);
//            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
//            InputStream is=connection.getInputStream();
//            FileOutputStream fos=new FileOutputStream(file);
//            byte[] buf = new byte[1024];
//            connection.connect();
//            double count=0;
//            if (connection.getResponseCode()>=500){
//                Toast.makeText(this, "链接超时", Toast.LENGTH_SHORT).show();
//            }else {
//                while (count<=100){
//                    if (is!=null){
//                        int numRead=is.read(buf);
//                        if (numRead<=0){
//                            break;
//                        } else {
//                          fos.write(buf,0,numRead);
//                        }
//                    }else {
//                        break;
//                    }
//                }
//            }
//            connection.disconnect();
//            fos.close();
//            is.close();
////            File file1=new File("/sdcard/youdao/youdao.apk");//下载后的路径
//            Toast.makeText(this, "下载完成", Toast.LENGTH_SHORT).show();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FiannceHttpMannager.getApiModel().downLoadApk(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        Log.d("AutoLoginService", "zkh3");
                        FileOutputStream fos = null;
                        File instanll = new File("/sdcard/" + apkName);
                        if (instanll.exists()) {
                            instanll.delete();
//                            return;
                        }
                        try {
                            //总长度
                            totaBute = responseBody.contentLength();
                            int len = -1;
                            byte[] bytes = new byte[512];
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
                            Toast.makeText(AutoLoginService.this, "下载完成", Toast.LENGTH_SHORT).show();
                            mNotificationManager.notify(2,notification.build());

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
}