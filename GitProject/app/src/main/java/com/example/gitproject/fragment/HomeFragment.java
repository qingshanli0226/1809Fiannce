package com.example.gitproject.fragment;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ProgressView;
import com.example.gitproject.R;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


public class HomeFragment extends BaseFragment {

    private Banner homeBanner;
    private ProgressView homeProgress;
    private TextView homeName;
    private TextView homeYearRate;
    private Button homePay;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        homeBanner = (Banner) inflate.findViewById(R.id.home_banner);
        homeProgress = (ProgressView) inflate.findViewById(R.id.home_progress);
        homeName = (TextView) inflate.findViewById(R.id.home_name);
        homeYearRate = (TextView) inflate.findViewById(R.id.home_yearRate);
        homePay = (Button) inflate.findViewById(R.id.home_pay);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        homeBanner.setImages(result.getImageArr());
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean = (HomeBean.ResultBean.ImageArrBean) path;
                Glide.with(context).load(imageArrBean.getIMAURL()).into(imageView);
            }
        }).start();

        HomeBean.ResultBean.ProInfoBean proInfo = CacheManager.getInstance().getHomeBean().getResult().getProInfo();
        homeProgress.startProgress(Integer.parseInt(proInfo.getProgress()), true);

        homeName.setText(proInfo.getName());

        homeYearRate.setText(proInfo.getYearRate());


    }
}