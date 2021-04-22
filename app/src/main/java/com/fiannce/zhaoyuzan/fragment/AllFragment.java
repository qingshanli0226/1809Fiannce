package com.fiannce.zhaoyuzan.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.view.ProgressView;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.zhaoyuzan.R;
import com.fiannce.zhaoyuzan.adapter.InvestAdapter;
import com.fiannce.zhaoyuzan.invest.IInvestView;
import com.fiannce.zhaoyuzan.invest.InvestPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends BaseFragment<InvestPresenter> implements IInvestView {

    private List<InvestBean.ResultBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private InvestAdapter investAdapter;

    @Override
    protected void initPresenter() {
        httpPresenter = new InvestPresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getProductData();
    }

    @Override
    protected void initView() {
        recyclerView = mBaseView.findViewById(R.id.allRv);

        investAdapter = new InvestAdapter(R.layout.all_item,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(investAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void onProductData(InvestBean investBean) {
        List<InvestBean.ResultBean> result = investBean.getResult();
        list.addAll(result);

        investAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg.toString(), Toast.LENGTH_SHORT).show();
    }
}
