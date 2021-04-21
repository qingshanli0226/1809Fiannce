package com.Fiannce.myapplication;


import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;

import com.youth.banner.Banner;

import java.util.List;

@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {


    @Override
    protected void initView() {



    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();


        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();



    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }


}
