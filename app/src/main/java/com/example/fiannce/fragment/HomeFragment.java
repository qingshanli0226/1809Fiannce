package com.example.fiannce.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.fiannce.R;
import com.example.fiannce.custom_view.CustomView_HomeFragment;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private Banner banner;
    private TextView text;
    private TextView name;
    private CustomView_HomeFragment customview;
    private TextView expect;
    private Button buy;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        LogUtils.json(imageArr);

        banner.setImages(imageArr);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean img = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = img.getIMAURL();
                Glide.with(getContext()).load(imaurl).into(imageView);
            }
        });
        banner.start();

        customview.SealedProgress(80,true);
    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        text = (TextView) mView.findViewById(R.id.text);
        name = (TextView) mView.findViewById(R.id.name);
        customview = (CustomView_HomeFragment) mView.findViewById(R.id.customview);
        expect = (TextView) mView.findViewById(R.id.expect);
        buy = (Button) mView.findViewById(R.id.buy);
    }
}