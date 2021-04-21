package com.example.a1809zg.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809zg.R;
import com.example.a1809zg.welcome.Ipresenter;
import com.example.a1809zg.welcome.Iview;
import com.example.frame.Basefragment;
import com.example.frame.Basepresenter;
import com.example.frame.CacheMore;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdataBean;
import com.scwang.smartrefresh.layout.BuildConfig;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class BlankFragment extends Basefragment<Ipresenter> implements Iview {

    private Banner ban;

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onUpdaData(UpdataBean updataBean) {

    }

    @Override
    protected int Findlayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initView() {


        ban = findViewById(R.id.ban);
    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheMore.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        if (BuildConfig.DEBUG) Log.d("BlankFragment", "homeBean:" + homeBean);
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();
        ban.setImages(imageArr);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean a= (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = a.getIMAURL();
                Glide.with(context).load(imaurl).into(imageView);
            }
        }).start();
    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}