package com.example.myapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyMoneyAdapter;
import com.example.myapplication.welcome.IWelcomeView;
import com.example.myapplication.welcome.WelcomePresenter;

import java.util.List;


public class Money_OneFragment extends BaseFragment<WelcomePresenter> implements IWelcomeView {


    private ImageView img;
    private MyMoneyAdapter adapter;
    private RecyclerView moneyOneRv;

    @Override
    protected void initData() {

        httpPresenter.getProductData();
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

    }

    @Override
    public void showLoading() {
        img.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        img.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}