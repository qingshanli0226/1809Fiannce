package com.fiannce.zhaoyuzan.fragment;


import android.content.Context;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.manager.CacheManager;

import com.fiannce.net.mode.HomeBean;
import com.fiannce.zhaoyuzan.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private Banner banner;
    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    private Toolbar toolBar;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();

        list.addAll(imageArr);

        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean bean = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = bean.getIMAURL();
                Glide.with(HomeFragment.this).load(imaurl).transform(new CenterCrop()).into(imageView);
            }
        });
        banner.start();


    }

    @Override
    protected void initView() {
        banner = mBaseView.findViewById(R.id.banner);
        toolBar = mBaseView.findViewById(R.id.toolbar);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
