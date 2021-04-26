package com.example.a1809fiannce.main.invest.allfinancial;

import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.net.model.AllProductBean;

import java.util.List;


public class AllFragment extends BaseFragment<AllPresenter> implements IAllView {

    private RecyclerView fragAllProductRv;
    private AllProductAdapter allProductAdapter;


    private int item = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initData() {
        httpPresenter.getAllProductData();

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new AllPresenter(this);
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
        allProductAdapter = new AllProductAdapter(result);
        fragAllProductRv.setAdapter(allProductAdapter);

        allProductAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            result.remove(position);
            TextView delet = view.findViewById(R.id.all_investment_delet);
            view.scrollTo(-delet.getWidth(),0);
            allProductAdapter.notifyItemRemoved(position);
        });

    }

    @Override
    public void showLoading() {
        loadingPage.showTransparencyLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSucessView();
    }

    @Override
    public void Error(String error) {
        loadingPage.showErrorView(error.trim());
    }

}