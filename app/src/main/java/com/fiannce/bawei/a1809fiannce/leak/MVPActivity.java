package com.fiannce.bawei.a1809fiannce.leak;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.a1809fiannce.welcome.IWelcomeView;
import com.fiannce.bawei.a1809fiannce.welcome.WelcomePresenter;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private List<Bitmap> bitmapList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);

    }

    @Override
    protected void initData() {
        httpPresenter.getVersionData();

    }

    @Override
    protected void initView() {
        addBitmap();

    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

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
}
