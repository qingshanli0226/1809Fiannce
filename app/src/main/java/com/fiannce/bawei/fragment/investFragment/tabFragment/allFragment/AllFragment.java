package com.fiannce.bawei.fragment.investFragment.tabFragment.allFragment;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.view.SlideRecyclerView;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.zhaoyuzan.R;
import com.fiannce.bawei.fragment.investFragment.adapter.InvestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends BaseFragment<InvestPresenter> implements IInvestView {

    private List<InvestBean.ResultBean> list = new ArrayList<>();
    private SlideRecyclerView recyclerView;
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

        investAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.txt_delete:
                        recyclerView.closeMenu();
                        list.remove(position);
                        investAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void onProductData(InvestBean investBean) {
        List<InvestBean.ResultBean> result = investBean.getResult();
        list.addAll(result);
        loadingPage.showSuccessView();
        investAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        loadingPage.showError(msg);
    }
}
