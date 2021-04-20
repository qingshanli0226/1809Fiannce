package com.example.a1809fiannce.home;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.net.model.HoemBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HoemFragment extends BaseFragment {

    private Banner fragHomeBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hoem;
    }

    @Override
    protected void initData() {

        HoemBean hoemBean = CacheManager.getInstance().getHoemBean();
        List<HoemBean.ResultBean.ImageArrBean> imageArr = hoemBean.getResult().getImageArr();

        fragHomeBanner.setImages(imageArr);
        fragHomeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HoemBean.ResultBean.ImageArrBean bean = (HoemBean.ResultBean.ImageArrBean) path;
                Glide.with(context).load(bean.getIMAURL()).into(imageView);
            }
        });
        fragHomeBanner.start();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragHomeBanner = (Banner) inflate.findViewById(R.id.frag_home_banner);
    }
}