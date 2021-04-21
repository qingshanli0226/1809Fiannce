package com.example.a1809fiannce.investment.allfinancial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.adapter.AllfinancialAdapter;
import com.example.framework.BaseFragment;
import com.example.net.mode.AllfinancialBean;

import java.util.ArrayList;
import java.util.List;

public class AllfinancialFragment extends BaseFragment<AllfinancialPresenter> implements IAllfinancial {
    private RecyclerView allfinancialRv;
    private List<AllfinancialBean.ResultBean> list=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allfinancial;
    }

    @Override
    protected void initView() {
        allfinancialRv = (RecyclerView) findViewById(R.id.allfinancial_rv);
    }

    @Override
    protected void initData() {
        httpPresenter.getAllfinancial();
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new AllfinancialPresenter(this);
    }

    @Override
    public void onAllfinancial(AllfinancialBean allfinancialBean) {

        list.addAll(allfinancialBean.getResult());

        AllfinancialAdapter allfinancialAdapter = new AllfinancialAdapter(list);
        allfinancialRv.setAdapter(allfinancialAdapter);
        allfinancialRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }
}