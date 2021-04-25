package com.example.fiannce.fragment.fragment_invest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiannce.R;
import com.example.fiannce.adapter.AllAdapter_AllFragment;
import com.example.fiannce.bean.BeanBack;
import com.example.framework.BaseFragment;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.UpdateBean;

public class AllFragment extends BaseFragment<UpdatePresenter_AllFragment> implements BeanBack {

    private RecyclerView rv;

    @Override
    protected void initData() {
        httpPresenter = new UpdatePresenter_AllFragment(this);
        httpPresenter.AllData();
    }

    @Override
    protected void initView() {
        rv = (RecyclerView) mView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void HomeData(HomeBean homeBean) {

    }

    @Override
    public void UpdateData(UpdateBean updateBean) {

    }

    @Override
    public void AllData(AllBean allBean) {
        AllAdapter_AllFragment allAdapter = new AllAdapter_AllFragment(R.layout.item_allfragment, allBean.getResult());
        rv.setAdapter(allAdapter);
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
}