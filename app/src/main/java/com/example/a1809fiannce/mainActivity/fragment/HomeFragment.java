package com.example.a1809fiannce.mainActivity.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    private Banner homeBanner;

    public HomeFragment() {
        // Required empty public constructor
    }

    View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return inflate;
    }

    private void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();
        list.addAll(imageArr);
        initBanner();
    }

    private void initView() {
        homeBanner = (Banner) inflate.findViewById(R.id.home_banner);
    }

    private void initBanner() {
        homeBanner.setImages(list);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean h = (HomeBean.ResultBean.ImageArrBean) path;
                Glide.with(context).load(h.getIMAURL()).into(imageView);
            }
        });
        homeBanner.isAutoPlay(true);
        homeBanner.setBannerStyle(BannerConfig.CENTER);
        homeBanner.start();
    }


}
