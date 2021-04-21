package com.example.a1809fiannce.invest.allfinancial;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.adapter.AllProductAdapter;
import com.example.framework.BaseFragment;
import com.example.net.model.AllProductBean;

import java.util.List;


public class AllFragment extends BaseFragment<AllPresenter> implements IAllView {

    private RecyclerView fragAllProductRv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new AllPresenter(this);
        httpPresenter.getAllProductData();
    }

    @Override
    protected void initView() {
        fragAllProductRv = (RecyclerView) findViewById(R.id.frag_allProduct_rv);
        fragAllProductRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onAllProductData(AllProductBean allProductBean) {
        LogUtils.json(allProductBean);
        List<AllProductBean.ResultBean> result = allProductBean.getResult();
        AllProductAdapter allProductAdapter = new AllProductAdapter(result);
        fragAllProductRv.setAdapter(allProductAdapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hileLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), "错误" + error, Toast.LENGTH_SHORT).show();
    }
}