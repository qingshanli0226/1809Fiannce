package com.finance.zg6.ui;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.finance.framework.BaseFragment;
import com.finance.framework.manager.CacheManager;
import com.finance.net.bean.HomeBean;
import com.finance.zg.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class MainFragment extends BaseFragment {

    private Banner banner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
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
                Glide.with(getContext()).load(imaurl+"").into(imageView);
            }
        });
        banner.start();
    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
    }
}