package com.example.a1809fiannce.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ProgressView;
import com.example.net.model.HoemBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HoemFragment extends BaseFragment {

    private Banner fragHomeBanner;
    private ProgressView fragHomePv;
    private TextView fragHomeTitle;
    private TextView homeFragYearRate;
    private TextView fragHomeBannerText;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hoem;
    }

    @Override
    protected void initData() {

        HoemBean hoemBean = CacheManager.getInstance().getHoemBean();

        ArrayList<String> strings = new ArrayList<>();
        strings.add("分享砍学费");
        strings.add("人脉总动员");
        strings.add("想不到你是这样的app");
        strings.add("购物节,爱不单行");

        List<HoemBean.ResultBean.ImageArrBean> imageArr = hoemBean.getResult().getImageArr();
        fragHomeBanner.setImages(imageArr);
        fragHomeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HoemBean.ResultBean.ImageArrBean bean = (HoemBean.ResultBean.ImageArrBean) path;
                Glide.with(context).load(bean.getIMAURL()).into(imageView);
            }
        });
        fragHomeBanner.setBannerAnimation(Transformer.CubeOut);
        fragHomeBanner.setIndicatorGravity(BannerConfig.RIGHT);
        fragHomeBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                fragHomeBannerText.setText(strings.get(position)+"");
            }
        });
        fragHomeBanner.start();



        HoemBean.ResultBean.ProInfoBean proInfo = hoemBean.getResult().getProInfo();
        fragHomePv.beginProgressAnimation(Integer.parseInt(proInfo.getProgress()), true);
        fragHomeTitle.setText(proInfo.getName()+"");
        homeFragYearRate.setText(proInfo.getYearRate()+"");


    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initView() {
        fragHomeBanner = (Banner) findViewById(R.id.frag_home_banner);
        fragHomePv = (ProgressView) findViewById(R.id.frag_home_pv);

        fragHomeTitle = (TextView) findViewById(R.id.frag_home_title);
        homeFragYearRate = (TextView) findViewById(R.id.home_frag_yearRate);
        fragHomeBannerText = (TextView) findViewById(R.id.frag_home_banner_text);
    }

    @Override
    public void onLeftImgClick() {
        Toast.makeText(getActivity(), "退出", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void destroy() {
        super.destroy();
        fragHomePv.destroy();
    }
}