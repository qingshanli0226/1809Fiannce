package com.fiannce.bawei.a1809fiannce.leak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MemActivity extends BaseActivity {
    private List<Bitmap> bitmapList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mem;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        addBitmap();
        handler.sendEmptyMessageDelayed(1,100*1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void initView() {

    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void addBitmap() {
        for(int i = 0; i < 100; i++) {
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
}
