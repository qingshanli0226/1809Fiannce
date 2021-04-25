package com.example.designed.bufragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.blankj.utilcode.util.LogUtils;
import com.example.designed.R;
import com.example.designed.adapter.LiAdapter;
import com.example.designed.welcome.IWelcomeView;
import com.example.designed.welcome.WelcomePresenter;
import com.example.net.BuildConfig;
import com.fiannce.bawei.framework.BaseFragment;
import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.VersionBean;

import java.util.ArrayList;
import java.util.List;

import top.littlefogcat.danmakulib.danmaku.Danmaku;
import top.littlefogcat.danmakulib.danmaku.DanmakuManager;

import static top.littlefogcat.danmakulib.danmaku.Danmaku.COLOR_RED;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanFragment extends BaseFragment implements IBaseView, IWelcomeView {

    private FrameLayout fragment;
    LiAdapter liAdapter;
    private WelcomePresenter welcomePresenter;
    private RecyclerView rv;
    List<Libean.ResultBean> list = new ArrayList<>();

    public QuanFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {
        welcomePresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        welcomePresenter.getLiData();


    }

    @Override
    protected void initView() {
        fragment = (FrameLayout) findViewById(R.id.fragment);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        DanmakuManager dm = DanmakuManager.getInstance();
        dm.setDanmakuContainer(fragment);
        dm.init(getContext(),fragment);

        Danmaku danmaku = new Danmaku();
        danmaku.text="有道金融壕运当头,首投返现最高达188元";
        danmaku.size=100;
        danmaku.color = COLOR_RED;
        dm.send(danmaku);




    }

    @Override
    protected int getLoutId() {
        return R.layout.fragment_quan;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
    public void onLiData(Libean libean) {
        list.addAll(libean.getResult());
        LogUtils.json(libean);
        liAdapter= new LiAdapter(R.layout.layout, list);

        rv.setAdapter(liAdapter);
    }
}
