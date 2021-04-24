package com.example.myapplication.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyMoneyAdapter;
import com.example.myapplication.demo.Demo;
import com.example.myapplication.welcome.IWelcomeView;
import com.example.myapplication.welcome.WelcomePresenter;

import java.util.List;


public class Money_OneFragment extends BaseFragment<WelcomePresenter> implements IWelcomeView {


    private ImageView img;
    private MyMoneyAdapter adapter;
    private RecyclerView moneyOneRv;
    private TextView light;
    private AnimationDrawable animationDrawable;

    @Override
    protected void initData() {

        httpPresenter.getProductData();
        light.setSelected(true);


    }

//    public void startAnimation(){
//
//        animationDrawable = new AnimationDrawable();
//
//        Drawable drawable1 = getResources().getDrawable(R.drawable.wc_ac_01);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.wc_ac_02);
//        Drawable drawable3 = getResources().getDrawable(R.drawable.wc_ac_03);
//        Drawable drawable4 = getResources().getDrawable(R.drawable.wc_ac_04);
//        Drawable drawable5 = getResources().getDrawable(R.drawable.wc_ac_05);
//        Drawable drawable6 = getResources().getDrawable(R.drawable.wc_ac_06);
//        Drawable drawable7 = getResources().getDrawable(R.drawable.wc_ac_07);
//        Drawable drawable8 = getResources().getDrawable(R.drawable.wc_ac_08);
//        Drawable drawable9 = getResources().getDrawable(R.drawable.wc_ac_09);
//        Drawable drawable10 = getResources().getDrawable(R.drawable.wc_ac_10);
//        Drawable drawable11 = getResources().getDrawable(R.drawable.wc_ac_11);
//        Drawable drawable12 = getResources().getDrawable(R.drawable.wc_ac_12);
//        Drawable drawable13 = getResources().getDrawable(R.drawable.wc_ac_13);
//        Drawable drawable14 = getResources().getDrawable(R.drawable.wc_ac_14);
//        Drawable drawable15 = getResources().getDrawable(R.drawable.wc_ac_15);
//        Drawable drawable16 = getResources().getDrawable(R.drawable.wc_ac_16);
//        Drawable drawable17 = getResources().getDrawable(R.drawable.wc_ac_06);
//        Drawable drawable18 = getResources().getDrawable(R.drawable.wc_ac_07);
//        Drawable drawable19 = getResources().getDrawable(R.drawable.wc_ac_08);
//        Drawable drawable20 = getResources().getDrawable(R.drawable.wc_ac_09);
//        Drawable drawable21 = getResources().getDrawable(R.drawable.wc_ac_10);
//        Drawable drawable22 = getResources().getDrawable(R.drawable.wc_ac_11);
//        Drawable drawable23 = getResources().getDrawable(R.drawable.wc_ac_12);
//        Drawable drawable24 = getResources().getDrawable(R.drawable.wc_ac_13);
//        Drawable drawable25 = getResources().getDrawable(R.drawable.wc_ac_14);
//        Drawable drawable26 = getResources().getDrawable(R.drawable.wc_ac_15);
//
//        animationDrawable.addFrame(drawable1, Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable2,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable3,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable4,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable5,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable6,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable7,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable8,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable9,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable10,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable11,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable12,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable13,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable14,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable15,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable16,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable17,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable18,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable19,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable20,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable21,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable22,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable23,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable24,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable25,Demo.ANIMATION_DUCTION);
//        animationDrawable.addFrame(drawable26,Demo.ANIMATION_DUCTION);
//
//        animationDrawable.setOneShot(true);
//    }

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

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
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
}