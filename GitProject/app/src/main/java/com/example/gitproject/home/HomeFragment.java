package com.example.gitproject.home;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ProgressView;
import com.example.gitproject.R;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


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
        homeBanner = (Banner) findViewById(R.id.home_banner);
        homeProgress = (ProgressView) findViewById(R.id.home_progress);
        homeName = (TextView) findViewById(R.id.home_name);
        homeYearRate = (TextView) findViewById(R.id.home_yearRate);
        homePay = (Button) findViewById(R.id.home_pay);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        ArrayList<String> strings = new ArrayList<>();
        String home_banner_one = getString(R.string.home_banner_one);
        String home_banner_two = getString(R.string.home_banner_two);
        String home_banner_three = getString(R.string.home_banner_three);
        String home_banner_four = getString(R.string.home_banner_four);
        strings.add(home_banner_one);
        strings.add(home_banner_two);
        strings.add(home_banner_three);
        strings.add(home_banner_four);
        //轮播图
        homeBanner.setImages(result.getImageArr());
        homeBanner.setBannerAnimation(Transformer.Tablet);
        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        homeBanner.setBannerTitles(strings);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean = (HomeBean.ResultBean.ImageArrBean) path;
                Glide.with(context).load(imageArrBean.getIMAURL()).into(imageView);
            }
        }).start();

        //布局设置
        HomeBean.ResultBean.ProInfoBean proInfo = CacheManager.getInstance().getHomeBean().getResult().getProInfo();
        homeProgress.startProgress(Integer.parseInt(proInfo.getProgress()), true);

        homeName.setText(proInfo.getName());

        homeYearRate.setText(proInfo.getYearRate());


    }


    @Override
    public void onClickCenter() {
        Toast.makeText(getActivity(), "onClickCenter", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClickLeft() {
        Toast.makeText(getActivity(), "onClickLeft", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickRight() {
        Toast.makeText(getActivity(), "onClickRight", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void destroy() {
        super.destroy();
        homeProgress.destroy();
    }

}