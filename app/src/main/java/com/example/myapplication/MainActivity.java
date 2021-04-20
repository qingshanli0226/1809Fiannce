package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {

    private List<String> list = new ArrayList<>();
    private Banner banner;
    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void initData() {

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();

        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            list.add(imaurl);
        }

        banner.setImages(imageArr);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean s = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = s.getIMAURL();
                Glide.with(MainActivity.this).load(imaurl).into(imageView);
            }
        }).start();

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        banner = (Banner) findViewById(R.id.banner);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}