package com.example.user.autologin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.demo.Demo;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.model.AutoLoginBean;
import com.example.net.RetrofitCretor;
import com.example.sp.SpUtils;
import com.example.user.R;

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
    public AutoLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public AutoLoginService getMyService() {
            return AutoLoginService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!SpUtils.getString(AutoLoginService.this).equals("")){
            new PersonAutoLoginPresenter(new IPersonLoginAutoView() {
                @Override
                public void autoLogin(AutoLoginBean autoLoginBean) {
                    LogUtils.json(autoLoginBean);
//                AutoLoginBean.ResultBean result = autoLoginBean.getResult();
//                String token = result.getToken();
                    if (autoLoginBean.getCode().equals("200")){
                        FiannceUserManager.getInstance().setLogin(true);
                        Toast.makeText(AutoLoginService.this, "自动登录成功"+FiannceUserManager.getInstance().isLogin(), Toast.LENGTH_SHORT).show();
                        SpUtils.putString(AutoLoginService.this,autoLoginBean.getResult().getToken());
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

    public void downLoad(String url, Handler handler){
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_more_on);
        builder.setProgress(100,0,false);
        builder.setContentTitle("下载中");
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
                            }
                            builder.setContentTitle("下载完成");
                            builder.setProgress((int) totalByte, count,false);
                            manager.notify(2,builder.build());
                            ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();

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

}