package com.example.a1809fiannce.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ProgressView;
import com.example.framework.view.ToolBar;
import com.example.net.mode.HomeBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

public class HomeFragment extends BaseFragment {
    private ToolBar toolbar;
    private Banner bann;
    private ProgressView pro;
    private TextView homeTitle;
    private TextView homeFragYearRate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        bann = (Banner) findViewById(R.id.bann);
        pro = (ProgressView) findViewById(R.id.pro);
        homeTitle = (TextView) findViewById(R.id.home_title);
        homeFragYearRate = (TextView) findViewById(R.id.home_frag_yearRate);
    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        pro.saledProgress(Integer.parseInt(homeBean.getResult().getProInfo().getProgress()),true);
        bann.setImages(homeBean.getResult().getImageArr());
        bann.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        homeTitle.setText(homeBean.getResult().getProInfo().getName());
        homeFragYearRate.setText(homeBean.getResult().getProInfo().getYearRate()+"%");

        bann.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean= (HomeBean.ResultBean.ImageArrBean) path;

                Glide.with(context).load(imageArrBean.getImaurl()).into(imageView);
            }
        });

        bann.start();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void destroy() {
        super.destroy();
        pro.destroy();
    }
}