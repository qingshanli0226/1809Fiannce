package com.example.a1809fiannce.mainActivity.fragment.more;

import android.graphics.drawable.AnimationDrawable;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.mainActivity.fragment.more.adapter.MyMoneyAdapter;
import com.example.a1809fiannce.welcome.IWelcomeView;
import com.example.a1809fiannce.welcome.WelcomePresenter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.ProductBean;
import com.fiannce.bawei.net.mode.VersionBean;

import java.util.List;


public class Money_OneFragment extends BaseFragmnet<WelcomePresenter> implements IWelcomeView {


    private ImageView img;
    private MyMoneyAdapter adapter;
    private RecyclerView moneyOneRv;
    private TextView light;
    private AnimationDrawable animationDrawable;

    @Override
    protected void initData() {

        httpPresenter.getHomeData();
        light.setSelected(true);


    }


    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {
        img = (ImageView) mView.findViewById(R.id.img);
        moneyOneRv = (RecyclerView) mView.findViewById(R.id.money_one_rv);
        moneyOneRv.setLayoutManager(new LinearLayoutManager(getContext()));
        light = (TextView) mView.findViewById(R.id.light);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money__one;
    }


    public void onProductDara(ProductBean productBean) {
        List<ProductBean.ResultBean> result = productBean.getResult();
        adapter = new MyMoneyAdapter(result);
        moneyOneRv.setAdapter(adapter);
        loadingPage.showSuccessView();
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
//        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void showError(String error) {
//        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        loadingPage.showError(error);
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }
}