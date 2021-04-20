package com.example.myfinancial;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.net.bean.HomeBean;
import com.example.pay.PayActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private com.youth.banner.Banner ban;
    private HomeBean homeBean;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
         homeBean = CaCheHome.getInstance().getHomeBean();
        Log.d("MainActivity123", homeBean.toString());
        //轮播图
        initlbt();
    }

    private void initlbt() {
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        ban.setImages(imageArr);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean aa= (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = aa.getIMAURL();
                Log.d("MainActivity", imaurl);
                Glide.with(context).load(imaurl).into(imageView);
            }
        }).start();
    }

    @Override
    protected void initView() {
        ban = (Banner) findViewById(R.id.ban);
    }

    @Override
    protected int getbandLayout() {
        return R.layout.activity_main;
    }


}