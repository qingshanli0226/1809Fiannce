package com.finance.zg6.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.finance.framework.BaseFragment;
import com.finance.framework.manager.CacheManager;
import com.finance.net.bean.HomeBean;
import com.finance.zg.R;
import com.finance.zg6.ui.view.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HomeFragment extends BaseFragment {

    private Banner banner;
    private TextView homeTitle;
    private ProgressView progressView;
    private TextView annualInterestRate;
    private TextView homeText;

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
                Glide.with(getContext()).load(imaurl + "").into(imageView);
            }
        });
        banner.start();

        homeTitle.setText("" + homeBean.getResult().getProInfo().getName());
        annualInterestRate.setText("预期年利率："+homeBean.getResult().getProInfo().getYearRate()+"%");
        progressView.startProgress(Integer.valueOf(homeBean.getResult().getProInfo().getProgress()),true);
    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        homeTitle = (TextView) mView.findViewById(R.id.home_title);
        progressView = (ProgressView) mView.findViewById(R.id.progress_view);
        annualInterestRate = (TextView) mView.findViewById(R.id.annual_interest_rate);
        homeText = (TextView) mView.findViewById(R.id.home_text);
    }
}