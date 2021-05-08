package com.example.a1809fiannce.main.fragment.investment.fragment;


import android.graphics.drawable.AnimationDrawable;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.main.fragment.more.adapter.MyMoneyAdapter;
import com.example.a1809fiannce.welcome.IWelcomeView;
import com.example.a1809fiannce.welcome.WelcomePresenter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.ProductBean;
import com.fiannce.bawei.net.mode.VersionBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllfinancialFragment extends BaseFragmnet<WelcomePresenter> implements IWelcomeView {


    private ImageView img;
    private TextView light;
    private RecyclerView AllfinancialRecyclerView;
    private MyMoneyAdapter adapter;
    private AnimationDrawable animationDrawable;

    public AllfinancialFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allfinancial;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getProductData();
        light.setSelected(true);
    }
    @Override
    protected void initView() {
        img = (ImageView) mView.findViewById(R.id.img);
        light = (TextView) mView.findViewById(R.id.light);
        AllfinancialRecyclerView = (RecyclerView) mView.findViewById(R.id.Allfinancial_Recycler_view);
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
        AllfinancialRecyclerView.setAdapter(adapter);
        AllfinancialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        loadingPage.showSuccessView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
