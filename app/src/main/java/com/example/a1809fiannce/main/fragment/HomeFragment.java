package com.example.a1809fiannce.main.fragment;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.view.ProgressView;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragmnet {

    private Banner banner;
    private ProgressView progressBar;
    private List<String> bannertitles = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {

    }

    protected void initData() {
        initBanner();
    }

    protected void initView() {
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        banner = (Banner) mView.findViewById(R.id.banner);
        progressBar = (ProgressView) mView.findViewById(R.id.progress_bar);
        initprogessbar();
    }

    private void initprogessbar() {
        progressBar.saledProgress(90,true);
    }


    private void initBanner() {

        bannertitles.add("分享砍学费");
        bannertitles.add("人脉总动员");
        bannertitles.add("想不到你是这样的app");
        bannertitles.add("购物节，爱不单行");

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        banner.setImages(homeBean.getResult().getImageArr());
        banner.setBannerAnimation(Transformer.Tablet);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitles(bannertitles);

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean s = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = s.getIMAURL();
                Glide.with(getActivity()).load(imaurl).into(imageView);
            }
        }).start();
    }


}
