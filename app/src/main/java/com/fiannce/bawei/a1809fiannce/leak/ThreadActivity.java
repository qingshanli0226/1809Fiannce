package com.fiannce.bawei.a1809fiannce.leak;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.RetrofitCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreadActivity extends BaseActivity {
    private List<Bitmap> bitmapList = new ArrayList<>();
    private AsyncTask asyncTask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initPresenter() {



    }

    @Override
    protected void initData() {
        addBitmap();

    }

    private OkHttpClient okHttpClient;

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!thread.isInterrupted()) {
                Log.d("LQS", "线程。。。。。。。。。。。");
                try {

                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    Log.d("LQS", "中断线程。。。。。。。。。。。");
                    e.printStackTrace();
                    break;
                }
            }
        }
    });

    @Override
    protected void initView() {
        thread.start();
    }

    private void addBitmap() {
        for(int i = 0; i < 50; i++) {
            Log.d("LQS", " i = " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), com.fiannce.bawei.framework.R.mipmap.e);
            bitmapList.add(bitmap);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        //当页面销毁时，要把线程停掉
        thread.interrupt();//中断线程//当线程睡眠状态下，会产生中断异常，如果线程没有睡眠则不会产生中断异常

    }
}
