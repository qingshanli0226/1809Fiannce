package com.example.myapplication.fragment;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.example.myapplication.R;
import com.example.myapplication.view.ProgressView;
import com.example.myapplication.welcome.WelcomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


public class HomeFragment extends BaseFragment<WelcomePresenter> {
    private Banner banner;
    private ProgressView progressBar;

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

        progressBar.startProgressDraw(30,true);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        progressBar = (ProgressView) mView.findViewById(R.id.progress_bar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}