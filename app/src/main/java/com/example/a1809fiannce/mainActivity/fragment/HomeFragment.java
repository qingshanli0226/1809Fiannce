package com.example.a1809fiannce.mainActivity.fragment;


import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.fiannce.bawei.framework.view.ProgressView;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragmnet {

    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
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
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();
        list.addAll(imageArr);
        initBanner();
    }

    protected void initView() {
        banner= (Banner) findViewById(R.id.banner);
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
