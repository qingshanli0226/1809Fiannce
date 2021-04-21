package com.example.myapplication.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.example.model.VersionBean;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.welcome.IWelcomeView;
import com.example.myapplication.welcome.WelcomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<WelcomePresenter> {
    private Banner banner;

    @Override
    protected void initData() {

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        banner.setImages(homeBean.getResult().getImageArr());
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean s = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = s.getIMAURL();
                Glide.with(getActivity()).load(imaurl).into(imageView);
            }
        }).start();

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}